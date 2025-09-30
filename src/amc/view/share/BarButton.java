package amc.view.share;

import amc.view.comp.AmcButton;

public class BarButton extends AmcButton {
    private final String page;

    public BarButton(String name, String page) {
        super();
        this.page = page;
        this.setText(name);
        this.setMaximumSize(null);
        this.setMinimumSize(null);
        this.setPreferredSize(null);
    }

    public String getPage() { return page; }
}
