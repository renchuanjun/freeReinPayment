package org.open.utils;


import org.open.task.ITaskService;
import org.open.task.demo.Demo1Task;
import org.open.task.demo.Demo2Task;

/**
 * @author 任传君
 * @create 2018-07-17 15:59
 **/
public class TaskEnum {

    public enum Task{

        DEMO1_TASK("TestDemo1Task", Demo1Task.class),
        DEMO2_TASK("TestDemo2Task", Demo2Task.class);
        private String name;

        private Class aClass;

        public String getName() {
            return name;
        }

        public Class getaClass() {
            return aClass;
        }

        Task(String name, Class aClass) {
            this.name = name;
            this.aClass = aClass;
        }

        public static Class<ITaskService> getClass(String name){
            Class aclass = null;
            for (Task task:Task.values()) {
                if ((task.getName()).equals(name) ){
                    aclass = task.getaClass();
                }
            }
            return  aclass;
        }
    }

}
