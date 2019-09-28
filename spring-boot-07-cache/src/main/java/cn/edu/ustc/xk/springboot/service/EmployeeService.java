package cn.edu.ustc.xk.springboot.service;

import cn.edu.ustc.xk.springboot.bean.Employee;
import cn.edu.ustc.xk.springboot.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

/**
 * Created by xuke
 * Description:
 * Date: 2018-12-18
 * Time: 21:22
 */
// 在这进行统一配置，就无需在每个方法上配置cacheNames了, 在这可以指定cacheManager = "employeeCacheManager"，不指定就可以用默认的缓存管理器
@CacheConfig(cacheNames = "emp")
@Service
public class EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    /**
     *   将方法的返回值缓存起来，以后再要相同的数据，则直接从缓存中拿到，不用再调用方法了
     *   CacheManager管理多个Cache组件，对缓存的真正CRUD操作是在Cache组件中，每一个缓存组建都有自己唯一的名字；
     *     即CacheManager里面有很多的Cache块，每一个Cache快含有很多的key-value键值对
     *
     *   @Cacheable 的几个属性：
     *     1. cacheNames/value：这二者是一个意思，都是用来指定缓存组件的名字的,在这可以指定多个cacheNames，可以用{}阔起来表示；
     *
     *     2. key: 缓存数据使用的key，可以用key属性来制定我们自己定义的key。默认使用的是方法参数的值（即在我们这是 id -> Employee组成一个键值对）
     *               可以编写SpEL表达式： #id:表示的是取出参数id的值， #a0/#p0/#root.args[0]这三个表示的都是取出第一个参数的值，具体的使用方法啊可以参考PPT
     *               额可以按照自己的定义来写，如：getEmp[id] 可以写成key = "#root.methodName + '[' + #id + ']'"
     *
     *     3. keyGenerator: key的生成器，可以自己指定key的生成器的组件id。一般是key/keyGenerator二选一使用。
     *             跟上面指定key一样，我们也可以编写我们自己的keyGenerator,,在方法上指定keyGenerator = "myKeyGenerator"即可
     *
     *     4. cacheManager/cacheResolver：指定缓存管理器，用cacheManager管理Cache块，用cacheResolver来指定解析器，一般也是二者选一
     *
     *     5. condition: 指定符合条件的情况才缓存，如condition=#id > 0,表示的是参数传进来的id值大于0的我们才进行缓存
     *
     *     6. unless; 否定缓存，即当unless指定的条件为true时，方法的返回值就不会被缓存。可以获取到结果进行缓存。
     *                        如unless = "#result == null",如果结果是空的话我们就不缓存了。
     *
     *    7. sync: 是否使用异步模式,默认的情况下我们是将方法执行完，将方法的执行结果同步地存到缓存中。在这我们也可以设置成异步
     *
     * 原理：从自动配置类(CacheAutoConfiguration)入手,看它导入了哪些配置类，看到@Import(CacheConfigurationImportSelector.class)
     *       点击CacheConfigurationImportSelector，查看它的实现selectImports方法，打上断点，看到最后的imports有哪些缓存的配置类（有10个）：
     *       1）org.springframework.boot.autoconfigure.cache.GenericCacheConfiguration；
     *       2）org.springframework.boot.autoconfigure.cache.JCacheCacheConfiguration
     *       3）org.springframework.boot.autoconfigure.cache.EhCacheCacheConfiguration
             4）org.springframework.boot.autoconfigure.cache.HazelcastCacheConfiguration
     *       5）org.springframework.boot.autoconfigure.cache.InfinispanCacheConfiguration
     *       6）org.springframework.boot.autoconfigure.cache.CouchbaseCacheConfiguration
     *       7）org.springframework.boot.autoconfigure.cache.RedisCacheConfiguration
     *       8）org.springframework.boot.autoconfigure.cache.CaffeineCacheConfiguration
     *       9）org.springframework.boot.autoconfigure.cache.SimpleCacheConfiguration【默认】
     *       10）org.springframework.boot.autoconfigure.cache.NoOpCacheConfiguration
     *在yml文件中设置debug=true之后，可以在控制台可以找默认的缓存配置类是：SimpleCacheConfiguration
     * 接着查看SimpleCacheConfiguration这个类，查看它的源码可以看到它给容器中注册了一个缓存管理器：ConcurrentMapCacheManager，
     * 然后ConcurrentMapCacheManager点进去，可以看到它实现了CacheManager，可以看到它是用来获取和创建ConcurrentMapCache块的（createConcurrentMapCache方法里面可以看到），
     * 而ConcurrentMapCache又会把数据放到ConcurrentMap里面。
     *
     * @Cacheable 运行流程：给我们的getEmp()方法，ConcurrentMapCacheManager的getCache方法，ConcurrentMapCache的lookup方法和put方法打上断点。
     *   1. 当我们在浏览器中发送/emp/1请求之后，可以看到最先跳进来的是getCache()方法，而不是我们自己定义大的getEmp()方法。
     *       也就是说，方法运行之前，先会去查询Cache（缓存组件），按照cacheNames指定的名字来获取，即ConcurrentMapCacheManager会先获取相应的缓存块；
     *   2. 第一次调用的时候，我们的cache为null,它就会调用createConcurrentMapCache()方法帮我们创建一个名字为我们指定的名字emp的cache块，
     *      这是第一步，首先创建或者得到Cache块，然后我们放行，他会来到lookup()方法，即去Cache块中使用key来查找缓存的内容，默认的key是我们方法的参数
     *      具体的key是怎么生成的话，看到IDEA左下边Debugger里面有一个findCachedItem（在CacheAspectSupport类里面）方法里面有一个generateKey()方法来实现的，
     *      一直点generateKey()进去之后，可以看到它的实现，它是由一个KeyGenerator接口来实现的，具体对应的是实现类是SimpleKeyGenerator来实现的
     *      切换到SimpleKeyGenerator，看看它是怎么生成key的：
     *                   a.) 如果我们没有传参数的话，则key=new SimpleKey();
     *                   b.) 如果我们传进来一个参数，则key=参数的值
     *                   c.) 如果有多个参数，key=new SimpleKey(params)，即将我们的参数包起来，封装成一个SimpleKey.
     *    3. 由于我们是第一次放，肯定查到的是null,所以接下来就会调用我们的方法（getEmp方法）；
     *    4. 执行完我们的目标方法之后，将调用put()方法将方法的返回结果放置到缓存中。
     *
     * 总结@Cacheable：@Cacheable标注的方法执行之前会先检查缓存中是否有这个数据，默认按照参数的值作为key去查询缓存，如果没有就运行我们的目标方法并将结果放入缓存。
     *              核心：1）使用CacheManager(即ConcurrentMapCacheManager)按照名字来找到Cache(即ConcurrentMapCache)组件；
     *                    2）key是使用KeyGenerator来生成的，默认的是SimpleKeyGenerator来实现的。
     *
     *
     *
     * @param id
     * @return
     */
    @Cacheable(value = {"emp"})
    public Employee getEmp(Integer id){
        System.out.println("查询到了" + id + "号员工");
        Employee employee = employeeMapper.getEmployeeById(id);
        return employee;

    }
        /**
         * @CachePut :既调用方法，也更新缓存中的数据。要达到同步更新缓存的功能，必须要求存入缓存的key和更新缓存的key是一致的
         * 它的运行时机：先调用我们的目标方法，再将目标方法的返回结果缓存起来(可以debug看一下)
         *
         * 具体的场景：我们修改了数据库中的某个数据，同时应该更新缓存的数据
         * 测试步骤：1. 先查询1号员工，查到的结恶果会放置到缓存中；
         *           2. 以后查询的还是之前的结果；
         *           3. 更新1号员工的数据，即发送/emp请求，带上参数，如/emp?lastName=张三&gender=1
         *           4. 重新查询1号员工的数据，可以看到是之前我们更新之前的数据。不应该是我们修改之后的结果吗？
         *               原因是缓存是根据key-value来缓存的，之前我们缓存的时候使用的是key=1，value=xuke的这个用户的信息
         *               而我们第3步更新完的值也放入到缓存中去了，只是这次存放的key是employee，而不是id了，
         *               也就是说两次缓存的结果都放在同一个cache块下，但是存放的是在不同的key-value键值对中。
         *   解决办法： a. 使用我们传入的参数的员工的id，即key=employee.id;
         *              b. 由于@CachePut是运行完之后再在缓存中放入东西，所以我们也可以使用key=#result.id来放入缓存
         *                 正式由于这个原因，所以@Cacheable是不允许使用key=#result.id的，因为它是在目标方法运行之前按照key去找缓存的
         */
        @CachePut(value = "emp", key = "#result.id")// 也放在emp这个cache块里
        public Employee updateEmp(Employee employee){
            System.out.println("查询到了" + employee);
            employeeMapper.updateEmployee(employee);
            return employee;
        }

    /**
     * @CacheEvict 缓存清除,可以指定缓存中要清除的数据
     *             缓存中的数据被删掉的言外之意就是，你再查询该id的员工的 时候，就得去数据库中查询了
     *             也可以指定删除该缓存（即该cache块中所有的k-v对）中所有的数据，用allEntries=true来指定
     *             beforeInvocation=false,表示的是缓存的清除操作是否在方法执行之前执行，默认为false的意思是缓存的清除在方法执行之后执行
     *              方法体里如果出现异常，缓存则不会清除掉
     *            beforeInvocation=true,表示的是缓存的清除操作在方法执行之前执行，无论方法有没有异常，缓存都清除
     *
     * @param id
     */
    @CacheEvict(value = "emp",key = "#id")
    public void deleteEmp(Integer id){
        System.out.println("删除了" + id + "号员工");
        //  employeeMapper.deleteEmployeeById(id);

        // int i = 10/0;//在这模拟beforeInvocation的作用
        }

    /**
     * 在这指定了一个复杂的缓存规则，按照lastName，id，email作为key都进行了缓存
     * 在这里我们每次执行按照lastName进行查询的时候，都会执行SQL语句，因为下面的@CachePut都会起作用，分别按照id和email来进行缓存
     * 因为@CachePut是每次先执行方法，将返回结果再缓存起来的。
     *
     * 而每次按照id或者email查的时候，为啥不需要查数据库了呢？不也应该先执行方法再缓存的吗？
     * 因为当你再查询的时候，即发送/emp/1请求的时候，EmployeeController里面searchEmployee方法会进行拦截，直接调用的是上面的getEmp方法
     * 而该方法按照的是默认的参数即id来进行缓存的，由于已经缓存过了，所以直接取了就，不需要再执行SQL语句了
     *
     *
     * @param lastName
     * @return
     */
    @Caching(
                cacheable = {
                        @Cacheable(value = "emp", key = "#lastName")
                 },
                put = {
                        @CachePut(value = "emp", key = "#result.id"),
                        @CachePut(value = "emp", key = "#result.email")
                 }
                 )
        public Employee getEmpByLastName(String lastName){
            Employee employee = employeeMapper.getEmployeeByLastName(lastName);
            return employee;
        }

}
