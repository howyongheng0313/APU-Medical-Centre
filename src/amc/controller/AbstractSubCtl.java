package amc.controller;

public abstract class AbstractSubCtl {
    private final AmcCtl ROOT;

    protected AbstractSubCtl(AmcCtl ROOT) {
        this.ROOT = ROOT;
    }

    protected AmcCtl getROOT() { return ROOT; }
}
