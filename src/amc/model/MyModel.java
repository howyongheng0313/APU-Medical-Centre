package amc.model;

import amc.controller.DbMan;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import hashTest.PassHash;

// User superclass
public class MyModel {
    private int uuid;
    private LocalDate birth;
    private String email;
    private String hash64;
    private String salt64;
    private String Role;
    
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
    
    public String getRole(){return Role;}
    public void setRole(String Role){this.Role = Role;}
    
    public MyModel(LocalDate birth, int uuid, String email, String hash64, String salt64) {
        this.birth = birth;
        this.uuid = uuid;
        this.email = email;
        this.hash64 = hash64;
        this.salt64 = salt64;
        
    }
    
    // Identify the role of an input email
    public static String getRoleByEmail(String email){        
        List<List<String>> result = DbHandle.ROLEMAP.select(1, row -> row.get(0).equals(email));
        if(result==null || result.isEmpty()) return null;
        return result.get(0).get(1);
    }
    
    // Get user by email and password
    public static MyModel login(String email, String password){
        String role = getRoleByEmail(email); // Call out getRoleByEmail()
        if(role==null)return null;
        
        Path path = switch(role){
            case "Manager" -> Path.of("Manager.txt");
            case "Doctor" -> Path.of("Doctor.txt");
            case "Staff" -> Path.of("Staff.txt");
            case "Customer" -> Path.of("Customer.txt");
            default -> null;
        };
        if(path==null) return null;
        
        DbHandle <MyModel> db = new DbHandle <>(DbAdapter.MYMODEL, path);
        List<MyModel> result = db.select(1, user -> user.getEmail().equals(email));
        if(result==null || result.isEmpty()) return null;
        
        MyModel user = result.get(0);
        
        boolean ok = PassHash.checkPass(password, user.getHash64(), user.getSalt64());
        if(ok){
            user.setRole(role);
            return user;
        }else{
            return null;
        }
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
