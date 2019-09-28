package cn.edu.ustc.xk.bean;

/**
 * Created by 徐科 on 2018/11/13.
 */
public class Dog
{
    private String name;
    private int age;

    @Override
    public String toString()
    {
        return "Dog{" + "name='" + name + '\'' + ", age=" + age + '}';
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }
}
