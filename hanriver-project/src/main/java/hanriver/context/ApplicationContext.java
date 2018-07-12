package hanriver.context;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;

import hanriver.annotation.Autowired;
import hanriver.annotation.Component;
import hanriver.annotation.Controller;
import hanriver.annotation.Repository;

public class ApplicationContext {
    
    HashMap<String, Object> objPool = new HashMap<>();
    
    public ApplicationContext (String packageName) throws Exception {
        String filePath = packageName.replace(".", "/");
        
        File dir = Resources.getResourceAsFile(filePath);
        
        findClass(dir, packageName);
        
        injectDependency();
    }

    private void injectDependency() {
        Collection<Object> objList = objPool.values();
        
        for (Object obj : objList) {
            Class<?> clazz = obj.getClass();
            
            Method[] methods = clazz.getMethods();
            
            for (Method m : methods) {
                if (!m.getName().startsWith("set")) continue;
                if (m.getAnnotation(Autowired.class) == null) continue;
                if (m.getParameterTypes().length > 1) continue;
                Class<?> paramType = m.getParameterTypes()[0];
                try {
                    Object dependency = getBean(paramType);
                    System.out.println("GetBean");
                    m.invoke(obj, dependency);
                } catch (Exception e) {
                    System.out.println("error:" + e.getMessage());
                }
            }
        }
    }

    public void refresh() {
        injectDependency();
    }
    public Object getBean(Class<?> paramType) {
        Collection<Object> objlist = objPool.values();
        for (Object obj : objlist) {
            if (paramType.isInstance(obj)) return obj;
        }
        throw new RuntimeException(paramType.getName() + "타입의 객체가 존재하지않습니다.");
    }
    
    public Object getBean(String name) {
        Object obj = objPool.get(name);
        if (obj == null) throw new RuntimeException(name + "이름의 객체가 존재하지않습니다.");
        return obj;
    }
    
    public void addBean(String name, SqlSessionFactory obj) {
        objPool.put(name, obj);
    }

    private void findClass(File dir, String packageName) {
        File[] subFiles = dir.listFiles((File pathname) -> {
            if (pathname.isDirectory())
                return true;
            if (pathname.isFile() && pathname.getName().endsWith(".class"))
                return true;
            return false;
        });
        
        for (File subFile : subFiles) {
            if (subFile.isFile()) {
                String className = packageName + "." + subFile.getName().replaceAll(".class", "");
                createObject(className);
            } else {
                findClass(subFile, packageName + "." + subFile.getName());
            }
        }
    }

    private void createObject(String className) {
        try {
            // 클래스 이름으로 .class파일을 찾아 로딩
            Class<?> clazz = Class.forName(className);
            if (clazz.getAnnotation(Component.class) == null && clazz.getAnnotation(Controller.class) == null && clazz.getAnnotation(Repository.class) == null) {
                return;
            }
            // 객체를 저장할 때 사용할 이름을 알아낸다.
            String objName = getObjectName(clazz);
            
            //클래스 정보를 보고 기본 생성자를 알아낸다.
            Constructor<?> constructor = clazz.getConstructor();
            
            // 기본 생성자를 호출하여 객체를 생성한 후 객체 보관소에 저장한다.
            objPool.put(objName, constructor.newInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getObjectName(Class<?> clazz) {
        String objName = null;
        Component compAnno = clazz.getAnnotation(Component.class);
        if (compAnno != null) objName = compAnno.value();
        Controller ctrlAnno = clazz.getAnnotation(Controller.class);
        if (ctrlAnno != null) objName = ctrlAnno.value();
        Repository repoAnno = clazz.getAnnotation(Repository.class);
        if (repoAnno != null) objName = repoAnno.value();
        
        if (objName.length() == 0) {
            return clazz.getCanonicalName();
        } else {
            return objName;
        }
    }


}
