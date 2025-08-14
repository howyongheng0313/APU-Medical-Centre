package amc.model;

import java.util.ArrayList;
import java.util.List;
import amc.controller.Convertor;

public interface DbAdapter<T> {
    public T getMod(List<String> row); // Abstraction method 
    public List<String> getRow(T model);

    /*
    ** Anonymous class
    ** Instead of writting a new class, the code is shorter
    */
    public static final DbAdapter<MyModel> MYMODEL = new DbAdapter<>() {
        // Override getMod()
        @Override
        public MyModel getMod(List<String> row) {
            int size = row.size();
            return new MyModel(
                Convertor.DATE.convert(row.get(0)),
                Convertor.NUM.convert(row.get(1)).intValue(),
                row.get(2),
                row.get(size -2),// hash64
                row.get(size -1) // salt64
            );
        }

        // Override getRow()
        @Override
        public List<String> getRow(MyModel model) {
            List<String> row = new ArrayList<> ();
            row.add(Convertor.DATE.strify(model.getBirth()));
            row.add(Convertor.NUM.strify(model.getUuid()));
            row.add(model.getEmail());
            row.add(model.getHash64());
            row.add(model.getSalt64());
            return row;
        }
    };

    
    public static final DbAdapter<List<String>> DEFAULT = new DbAdapter<> () {
        @Override
        public List<String> getMod(List<String> row) {
            return row;
        }

        @Override
        public List<String> getRow(List<String> model) {
            return model;
        }
    };
}
