
package de.lathspell.test;

import de.lathspell.test.db.FilterRepository;
import de.lathspell.test.db.RoutingRepository;
import de.lathspell.test.db.ServiceRepository;
import de.lathspell.test.model.Filter;
import de.lathspell.test.model.Routing;
import de.lathspell.test.model.Service;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import lombok.extern.slf4j.Slf4j;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Application.class)
@Slf4j
public class InvertedForeignKeyTest {

//    @PersistenceUnit
//    private EntityManagerFactory emf;
    
    @Autowired
    private ServiceRepository srvRepo;
    
    @Autowired
    private RoutingRepository rtRepo;
    
    @Autowired
    private FilterRepository fRepo;
    
    @Test
    public void test1() {
    
        Service srv = new Service();
        assertNull(srv.getVersion());
        srvRepo.save(srv);
        assertNotNull(srv.getVersion());
        log.info("Service saved with ID {}",srv.getVersion());
        
        Routing r = new Routing();
        r.setService(srv);
        r.setUuid("ein-test");
        srv.setRouting(r);
        assertNull(r.getId());
        
        Filter f = new Filter();      
        r.setFilter(f);
        f.setRouting(r);
        //Der wichtige Punkt ist, dass nicht im Filter wo die eigentlich Redferenz steht
        //der Key gesetzt wird. Weder nur dort und auch nicht an beiden Stellen.
        // --> Was Funktioniert ist nur, angesichts der Hirachie, auf dem oberen Element die Verbindung zu setzen, auch wenn diese invers ist
        
        
        log.info("Saving Service");
        srvRepo.save(srv);
        srvRepo.flush();
        assertThat(srvRepo.findAll().size() == 1);
        
        assertThat(rtRepo.findAll().size() == 1);
        log.info("Routing gespeichert: {}", rtRepo.findAll().get(0).toString());
        log.info("Lokale Routing Referenz: {}", r.toString());
        
        assertThat(fRepo.findAll().size() == 1);
        assertNotNull(fRepo.findAll().get(0).getRouting());
        log.info("Filter gespeichert: {}", fRepo.findAll().get(0).toString());
        log.info("Lokale Routing Referenz: {}", f.toString());
    }
    
    
}
