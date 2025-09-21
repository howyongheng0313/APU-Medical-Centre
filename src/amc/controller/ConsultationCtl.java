/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package amc.controller;
import amc.view.doctor.Consultation;
import javax.swing.JPanel;
/**
 *
 * @author Administrator
 */
public class ConsultationCtl extends AbstractSubCtl{
    private final Consultation viewConsultation;
    
    public ConsultationCtl(AmcCtl ROOT){
        super(ROOT);
        ServiceCtl serviceCtl = new ServiceCtl(ROOT);
        this.viewConsultation = new Consultation(serviceCtl, null);
    }

    public JPanel getView(){
        return viewConsultation;
    }
}
