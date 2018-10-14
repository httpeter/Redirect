package case1.nl.controller;

import java.io.Serializable;
import case1.nl.entities.Mapping;
import case1.nl.util.FMessage;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@ManagedBean
@ViewScoped
public class IndexController implements Serializable {
    
    @ManagedProperty(value = "#{sessionController}")
    private SessionController sessionController;
    
    private List<Mapping> mappings;
    
    private String url;
    
    private Boolean showMapping;

    //<editor-fold defaultstate="collapsed" desc="comment">
    public SessionController getSessionController() {
        return sessionController;
    }
    
    public void setSessionController(SessionController sessionController) {
        this.sessionController = sessionController;
    }
    
    public List<Mapping> getMappings() {
        return mappings;
    }
    
    public String getUrl() {
        return url;
    }

    public Boolean getShowMapping() {
        return showMapping;
    }

    public void setShowMapping(Boolean showMapping) {
        this.showMapping = showMapping;
    }
    
    

//</editor-fold>
    public void redirect() {
        
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        url = request.getRequestURL().toString();
        
        mappings = this.sessionController.getDB().getResultList(Mapping.class);
        
        mappings.forEach(mapping -> {
            
            if (url.endsWith(mapping.getFromURL()) || url.endsWith((mapping.getFromURL() + "/"))) {
                
                FacesContext facesContext = FacesContext.getCurrentInstance();
                ExternalContext externalContext = facesContext.getExternalContext();
                
                try {
                    externalContext.redirect(mapping.getToURL());
                } catch (IOException ex) {
                    Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
                    FMessage.error(ex.getMessage());
                }
            }
            
        });

//     
    }
    
}
