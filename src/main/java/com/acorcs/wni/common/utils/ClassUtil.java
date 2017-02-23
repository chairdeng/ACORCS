package com.acorcs.wni.common.utils;

/**
 * Created by dengc on 2017/1/2.
 */
import com.acorcs.wni.mybatis.mapper.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class ClassUtil {

    /**
     * @Description: 根据一个接口返回该接口的所有类
     * @param c 接口
     * @return List<Class>    实现接口的所有类
     * @author 邓超
     * @date 2012-4-5 上午11:22:24
     */
    @SuppressWarnings("unchecked")
    public static List<Class> getAllClassByInterface(Class c){
        List returnClassList = new ArrayList<Class>();
        //判断是不是接口,不是接口不作处理
        returnClassList.add(CloudMapper.class);
        returnClassList.add(JetStreamMapper.class);
        returnClassList.add(StormCenterMapper.class);
        returnClassList.add(TropopauseMapper.class);
        returnClassList.add(TurbulenceMapper.class);
        returnClassList.add(VolcanoMapper.class);
        //TODO:缺陷不能写死
//        if(c.isInterface()){
//            String packageName = c.getPackage().getName();	//获得当前包名
//            try {
//                List<Class> allClass = getClasses(packageName);//获得当前包以及子包下的所有类
//
//                //判断是否是一个接口
//                for(int i = 0; i < allClass.size(); i++){
//                    if(c.isAssignableFrom(allClass.get(i))){
//                        if(!c.equals(allClass.get(i))){
//                            returnClassList.add(allClass.get(i));
//                        }
//                    }
//                }
//            } catch (Exception e) {
//                // TODO: handle exception
//            }
//        }
        return returnClassList;
    }

    /**
     *
     * @Description: 根据包名获得该包以及子包下的所有类不查找jar包中的
     * @param packageName 包名
     * @return List<Class>    包下所有类
     * @author 邓超
     * @date 2012-4-5 上午11:26:48
     */
    private static List<Class> getClasses(String packageName) throws ClassNotFoundException,IOException{
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = packageName.replace(".", "/");
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<File>();
        while(resources.hasMoreElements()){
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        ArrayList<Class> classes = new ArrayList<Class>();
        for(File directory:dirs){
            classes.addAll(findClass(directory, packageName));
        }
        return classes;
    }

    private static  List<Class> findClass(File directory, String packageName)
            throws ClassNotFoundException{
        List<Class> classes = new ArrayList<Class>();
        if(!directory.exists()){
            return classes;
        }
        File[] files = directory.listFiles();
        for(File file:files){
            if(file.isDirectory()){
                assert !file.getName().contains(".");
                classes.addAll(findClass(file, packageName+"."+file.getName()));
            }else if(file.getName().endsWith(".class")){
                classes.add(Class.forName(packageName+"."+file.getName().substring(0,file.getName().length()-6)));
            }
        }
        return classes;
    }
}
