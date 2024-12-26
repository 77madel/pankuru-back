package org.odk.g1.pankuru.Service.Service;

import org.odk.g1.pankuru.Entity.Visitor.Visitor;
import org.odk.g1.pankuru.Repository.VisitorRepository;
import org.springframework.stereotype.Service;

@Service
public class VisitorService {

    public VisitorRepository visitorRepository;

    public VisitorService(VisitorRepository visitorRepository) {
        this.visitorRepository = visitorRepository;
    }

    public Visitor saveVisitorInfo(Visitor visitor) {
        return visitorRepository.save(visitor);
    }

}
