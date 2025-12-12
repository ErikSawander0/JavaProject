package com.miun.martinclass.demo.blog.service;

public class blogService {
    /*As we discussed in class, the interaction between the blog and the site isn’t very complicated—but it can see
    tricky at first. To ease up with that here is generally how the logic looks like; Db -> Service -> Bean -> Site

     You will need to figure out the implementation yourself, but here are some general guidelines:
     Study the existing services.
     Look at what’s declared in the other service classes and why it’s there.

     The service layer connects the application and the database.
     It both receives data from the database and sends data to it. Much of this is handled automatically through Jakarta annotations.

     About Jakarta syntax (@ annotations).
     The @ annotations come from Jakarta and are used for many things, but in our case, they mainly help map objects to database entries.
     When you wonder what the database represents, look at the entity classes—each instance of an entity sort of corresponds to a row in the database.
     Basically we arnt feeding the database lines manually like we did in the db-course we are feeding it the litteral objects,
     while jakarta handles all that boring manual stuff itself. We are marking some variables in this case with things
     such as an ID tag, table names and well you get the picture.

     Look at the Entity package.
     Entities contain constructors and fields that represent the actual data stored in the database. If you’re unsure
     what data exists, check the entity classes.

     Do not modify the database implementation itself.
     Use the service layer to interact with the DB, but the underlying DB structure should remain untouched.*/
}
