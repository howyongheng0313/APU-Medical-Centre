package amc.model;

import amc.controller.DbMan;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// User superclass
public class MyModel {
    private int uuid;
    private LocalDate birth;
    private String email;
    private String hash64;
    private String salt64;
    
    public int getUuid() {return uuid;}
    public void setUuid(int uuid) {this.uuid = uuid;}

    public LocalDate getBirth() {return birth;}
    public void setBirth(LocalDate birth) {this.birth = birth;}
    
    public String getEmail(){return email;}
    public void setEmail(String email){this.email = email;}

    public String getHash64(){return hash64;}
    public void setHash64(String hash64){this.hash64 = hash64;}
    
    public String getSalt64(){return salt64;}
    public void setSalt64(String salt64){this.salt64 = salt64;}
    
    public MyModel(LocalDate birth, int uuid, String email, String hash64, String salt64) {
        this.birth = birth;
        this.uuid = uuid;
        this.email = email;
        this.hash64 = hash64;
        this.salt64 = salt64;
    }
    
    // Sample
    public static void main(String[] args) {
        Path my_P = Path.of("./database/MyModel.txt");
        DbHandle<MyModel> my_T = new DbHandle<> (DbAdapter.MYMODEL, my_P);
        List<MyModel> snowLs = new ArrayList<> ();
//      snowLs.add(new MyModel(LocalDate.now(), 175, "superuser2gmail.com"));
//      my_T.insert(snowLs);
        my_T.update(-1, DbMan.checkId(174), DbMan.birthPlus());
    }
}
