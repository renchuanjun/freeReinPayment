package org.open.utils;


import org.open.task.ITaskService;
import org.open.task.demo.TestDemoTask;

/**
 * @author 任传君
 * @create 2018-07-17 15:59
 **/
public class TaskEnum {

    public enum Task{

        INVRETURNRECORD_TASK("TestDemoTask", TestDemoTask.class);

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
