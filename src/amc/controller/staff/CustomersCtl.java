package amc.controller.staff;

import amc.controller.AbstractSubCtl;
import amc.controller.AmcCtl;
import amc.controller.share.JumpTree;
import amc.controller.share.ProfileCtl;
import amc.model.DbMan;
import amc.model.db_impl.Db;
import amc.model.entity.Customer;
import amc.view.staff.CustomersPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JPanel;

public class CustomersCtl extends AbstractSubCtl {
    private final CustomersPanel viewCustomers = new CustomersPanel();

    public CustomersCtl(AmcCtl ROOT) {
        super(ROOT);
        refreshCustomerLs();
        Db.Customer.addTblListener(() -> refreshCustomerLs());
        viewCustomers.picSearch.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) { refreshCustomerLs(); }
        });
        viewCustomers.tblCustomer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) { openCusProfile(evt); }
        });
    }

    private void refreshCustomerLs() {
        String search = viewCustomers.getSearchContent().toLowerCase();
        List<Customer> customerLs = Db.Customer.select(-1, DbMan.searchCusInfo(search));
        viewCustomers.renderCustomerLs(customerLs);
    }

    private void openCusProfile(MouseEvent evt) {
        if (evt.getClickCount() != 2) return;
        int selectedIndex = viewCustomers.tblCustomer.getSelectedRow();
        String selectedId = (String)viewCustomers.tblCustomer.getValueAt(selectedIndex, 0);
        ProfileCtl profileCtl = new ProfileCtl(
            getROOT(), Db.Customer.getById(selectedId), JumpTree.StfCustomer
        );
        profileCtl.startView();
    }

    public JPanel getView() { return viewCustomers; }
}
