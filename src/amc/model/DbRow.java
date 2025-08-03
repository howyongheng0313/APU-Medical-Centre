package amc.model;

import amc.controller.Convert;
import java.util.ArrayList;
import java.util.List;

public interface DbRow<T> {
    public T create(List<String> strRow);
    public List<String> getRow(T model);

    public static final DbRow<MyModel> MYMODEL = new DbRow<>() {
        @Override
        public MyModel create(List<String> strRow) {
            return new MyModel(
                Convert.DATE.convert(strRow.get(0)),
                Convert.NUM.convert(strRow.get(1)).intValue()
            );
        }

        @Override
        public List<String> getRow(MyModel model) {
            List<String> strRow = new ArrayList<> ();
            strRow.add(Convert.DATE.strify(model.getBirth()));
            strRow.add(Convert.NUM.strify(model.getUuid()));
            return strRow;
        }
    };
}
