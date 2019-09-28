package cn.edu.ustc.xk.springboot.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by xuke
 * Description:
 * Date: 2018-12-23
 * Time: 22:35
 */
@EnableWebSecurity // 这里面已经含有@Configuration注解了
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Override// ctrl+o重写该方法，定制请求的授权规则
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests().antMatchers("/").permitAll()
                .antMatchers("/level1/**").hasRole("VIP1")
                .antMatchers("/level2/**").hasRole("VIP2")
                .antMatchers("/level3/**").hasRole("VIP3");

        /**
         *      开启自动配置的登陆功能:
         *       1. 如果没有权限的话回来到登陆页面，formLogin()负责发出post形式的/login请求来到Spring Security封装的登陆页面；
         *       2. 重定向到/login?error表示登陆失败
         *             UsernamePasswordAuthenticationFilter类的部分源码：
         *                 public UsernamePasswordAuthenticationFilter() {
         *                 super(new AntPathRequestMatcher("/login", "POST"));
         *       3. 定制我们自己的登陆页面,发出/userlogin请求之后将会来到我们自己定义的登陆页面
         *          一旦定制了loginPage的话，那么loginPage里面的参数的post请求就是登陆处理url，
         *          即在表单里的th:action不再是我们Spring Security默认的/login的post请求了，而且Spring Security规定里面的
         *          th:action和我们定制的loginProcessingUrl的参数是一致的，Spring Security才能让你登陆.
         *          我们也可以使用loginProcessingUrl("/login")来设置登录请求的地址，登陆页面的action跟它一致即可。
         *          也是就是说它默认的loginProcessingUrl里面的参数就是我们loginPage里面的参数.
         *          所以在这有没有loginProcessingUrl("/userlogin")都是一个效果。
         *
         *        4. 疑问？为啥执行完了登陆会自动来到首页，即welcome页面呢（也就是说登陆了之后为啥会发出"/"请求呢？）？是跟上面的匹配规则有关系吗？
         *           需要阅读一下源码！！！
         *     }
         */
        http.formLogin()
                .usernameParameter("username")
                .passwordParameter("pwd")
                .loginPage("/userlogin");// 定制跳转到我们定制的登陆页面（在KungFuController里面），而且登陆的提交表单里的action在没指定loginProcessingUrl的前提下也必须是这个参数

        /**
         * 开启自动配置的注销功能：
         *    1.当页面发出/logout请求表示退出的时候，logout()方法将会负责退出，默认将会重新定向跳转到登陆页面并且后面还会带上logout，
         *      即/login?logout，清空session。所以我们可以使用logoutSuccessUrl可以定制退出时发出啥请求，咱们在这让它退出之后回到首页；
         *
         *    LogoutConfigurer类的部分源码：
         *    public final class LogoutConfigurer{
         * 	  private String logoutSuccessUrl = "/login?logout";
         * 	  private String logoutUrl = "/logout";
         *
         */
        http.logout().logoutSuccessUrl("/");
        /**
         * 开启记住我功能，记住我功能的意思是即使浏览器关了，下次再访问的是时候无需登陆
         * /login登陆页面会多一个复选框，显示Remember me on this computer.
         * 原理：当我们勾选了Remember me on this computer.功能，浏览器会在cookie里面为我们保存一个name叫remember me的cookie，
         *       以后我们访问页面的时候就会带上该cookie，只要通过检查就可以免登陆，默认14天之后会消失
         *       当我们点击注销的时候，即发出/logout请求的时候，该cookie将会消失
         *       cookie在fn+f12，找到Application，然后就能找到cookie了
         *
         *       rememberMeParameter里面的参数必须和页面的参数是一致的，同上面的username和pwd
         */
        http.rememberMe().rememberMeParameter("remember");
    }

    @Override// ctrl+o重写该方法，定制认证规则
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /**
         *  inMemoryAuthentication 从内存中获取。在inMemoryAuthentication()后面多了".passwordEncoder(new BCryptPasswordEncoder())",这相当于登陆时用BCrypt加密方式对用户密码进行处理。
         *  以前的".password("123456")" 变成了 ".password(new BCryptPasswordEncoder().encode("123456"))" ，这相当于对内存中的密码进行Bcrypt编码加密。比对时一致，说明密码正确，允许登陆。
         *  不加密的话会报错：There is no PasswordEncoder mapped for the id "null"
         *  官方解释：https://docs.spring.io/spring-security/site/docs/5.2.0.BUILD-SNAPSHOT/reference/htmlsingle/#community-help
         *  详细解答：https://blog.csdn.net/canon_in_d_major/article/details/79675033
         */

        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("zhangsan").password(new BCryptPasswordEncoder().encode("123456")).roles("VIP1", "VIP2")
                .and()
                .withUser("lisi").password(new BCryptPasswordEncoder().encode("123456")).roles("VIP2", "VIP3")
                .and()
                .withUser("wangwu").password(new BCryptPasswordEncoder().encode("123456")).roles("VIP1", "VIP3");
    }
}
