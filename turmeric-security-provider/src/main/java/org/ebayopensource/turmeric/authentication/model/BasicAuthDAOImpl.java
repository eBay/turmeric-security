package org.ebayopensource.turmeric.authentication.model;

import org.ebayopensource.turmeric.utils.jpa.AbstractDAO;

public class BasicAuthDAOImpl extends AbstractDAO implements BasicAuthDAO {

    @Override
    public BasicAuth getBasicAuth(String subjectName) {
        return getSingleResultOrNull(BasicAuth.class, "subjectName", subjectName);
    }
}
