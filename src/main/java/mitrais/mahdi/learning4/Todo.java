package mitrais.mahdi.learning4;
import java.util.*;

import org.omg.CORBA.portable.IDLEntity;


public class Todo {
    private int id;
    private String user;
    private String desc;
    private Date targetDate;
    private boolean isDone;
//Getters, Setters, Constructors, toString, equals and hash code
    public Todo (int Id, String User, String Desc, Date TargetDate, boolean IsDone){
        this.desc = Desc;
        this.id = Id;
        this.isDone = IsDone;
        this.targetDate = TargetDate;
        this.user = User;
    }

    public String getUser(){
        return this.user;
    }
}