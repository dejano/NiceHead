package rs.ac.uns.ftn.xmlbsep.dao;

import rs.ac.uns.ftn.xmlbsep.beans.jaxb.User;

public interface UserDaoLocal {

    User login(String username, String password);
    void logout();
}
