package amc.model;

import amc.controller.ROps;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MyModel {
    private LocalDate birth;
    private int uuid;

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public int getUuid() {
        return uuid;
    }

    public void setUuid(int uuid) {
        this.uuid = uuid;
    }

    public MyModel(LocalDate birth, int uuid) {
        this.birth = birth;
        this.uuid = uuid;
    }

    public static void main(String[] args) {
        Path my_P = Path.of("./database/MyModel.txt");
        DbHandle<MyModel> my_T = new DbHandle<> (DbRow.MYMODEL, my_P);
        List<MyModel> snowLs = new ArrayList<> ();
        snowLs.add(new MyModel(LocalDate.now(), 175));
//        my_T.insert(snowLs);
        my_T.update(-1, ROps.checkId(174), ROps.birthPlus());
    }
}
