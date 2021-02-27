package org.prodageo.exa2612lib;

public class sharedCounter 
{
    public static int theSharedCounter ;

    // a static variable : the same for all objects instances of the sharedCounter class
    private static int thePrivateStaticSharedCounter ;

    public static int getPrivateStaticSharedCounter ()
    {
        return thePrivateStaticSharedCounter ;
    }

    // a non-static variable : specific to instance object in which they are created. 
    private int thePrivateSharedCounter ;

    public int getPrivateSharedCounter ()
    {
        return thePrivateSharedCounter ;
    }

    public int incrementPrivateSharedCounter ()
    {
        thePrivateSharedCounter = thePrivateSharedCounter + 1 ;
        return thePrivateSharedCounter ;
    }


}
