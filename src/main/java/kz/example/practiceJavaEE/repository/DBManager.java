package kz.example.practiceJavaEE.repository;

import kz.example.practiceJavaEE.model.Tasks;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class DBManager {
    private static final ArrayList<Tasks> tasks = new ArrayList<>();
    private static Long id = 1L;


    static {
        tasks.add(new Tasks(id++, "Taks1", "task1's descroption", "2024-02-02", true));
        tasks.add(new Tasks(id++, "Taks2", "task2's descroption", "2024-03-02", false));
        tasks.add(new Tasks(id++, "Taks3", "task3's descroption", "2024-01-02", true));
    }

    public static ArrayList<Tasks> getAllTasks(){
        return tasks;
    }

    public static Tasks getTask(Long id){
        Tasks task = null;
        for (Tasks t : tasks){
            if (Objects.equals(t.getId(), id)){
                task = t;
            }
        }
        return task;
    }

    public static boolean saveTask(Tasks task){
        if (task != null){
            if (task.getId() == null) {
                task.setId(id++);
                tasks.add(task);
                return true;
            }
            for (Tasks t: tasks){
                if (t.getId().equals(task.getId())){
                    tasks.set(tasks.indexOf(t), task);
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean deleteTask(Long id){
        Iterator<Tasks> tasksIterator = tasks.iterator();
        while (tasksIterator.hasNext()){
            Tasks t = tasksIterator.next();
            if (t.getId().equals(id)){
                tasks.remove(t);
                return true;
            }
        }
        return false;
    }
}
