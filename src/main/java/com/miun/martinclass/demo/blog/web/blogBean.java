package com.miun.martinclass.demo.blog.web;

public class blogBean {
    /* As we discussed in class, the interaction between the blog and the site isn’t very complicated—but it can see
    tricky at first. To ease up with that here is generally how the logic looks like; Db -> Service -> Bean -> Site
    You will need to figure out the implementation yourself, but here are some general guidelines:
     *
     * Now to some more spesific instructions to BEAN, bean more or less sends data to website(XHTML) like I will say
     * with alot of these things, study the previous bean to see how they generally looks. Now, with the bean for the time
     being you do not have access to the real actual information from the database. So you can make simple dummy functions
     that forwards all the bits from the db entry. Should be relatively straight forward accept the image you will have
     to look up what a 'blob' is in relation to payara and jakarta.

     About Jakarta syntax (@ annotations).
     The @ annotations come from Jakarta and are used for many things, but in our case, they mainly help map objects to
     database entries.
     When you wonder what the database represents, look at the entity classes—each instance of an entity corresponds to
     a row in the database. Basically we arnt feeding the database lines manually like we did in the db-course we are
     feeding it the litteral objects, while jakarta handles all that boring manual stuff itself. We are marking some
     variables in this case with things such as an ID tag, table names and well you get the picture.

     Look at the Entity package.
     Entities contain constructors and fields that represent the actual data stored in the database. If you’re unsure
     what data exists, check the entity classes.
     */
}
