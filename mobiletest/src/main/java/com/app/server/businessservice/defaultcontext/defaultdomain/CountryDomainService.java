package com.app.server.businessservice.defaultcontext.defaultdomain;
import com.app.server.repository.organizationboundedcontext.location.CountryRepository;
import com.app.shared.organizationboundedcontext.location.Country;
import com.athena.framework.server.helper.RuntimeLogInfoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.athena.framework.server.exception.biz.SpartanConstraintViolationException;
import com.athena.framework.server.exception.repository.SpartanPersistenceException;

@Component
public class CountryDomainService {

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Autowired
    private CountryRepository<Country> countryRepository;

    public void countryDomainMethod(Country entity) throws SpartanPersistenceException, SpartanConstraintViolationException, Exception {
        if (entity.getCountryId() != null) {
        }
        entity.setCountryName(entity.getCountryName() + "nayan");
        com.app.shared.organizationboundedcontext.location.Country country1 = countryRepository.save(entity);
    }
}
