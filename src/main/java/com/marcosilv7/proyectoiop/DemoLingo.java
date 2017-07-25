package com.marcosilv7.proyectoiop;

import com.lindo.Lingd14;
import com.marcosilv7.proyectoiop.configuracion.ProyectoiopApplication;
import org.springframework.boot.SpringApplication;


public class DemoLingo {

    private Lingd14 lng = new Lingd14();
    private Object pLngEnv;
    private int nLastIterationCount;
    private String urlLingo;
    private String urlLog;

    // Load the Lingo JNI interface

    public DemoLingo(String urlLingo,String urlLog){
        this.urlLingo=urlLingo;
        this.urlLog=urlLog;

        pLngEnv = lng.LScreateEnvLng();
        if ( pLngEnv == null)
        {

            System.out.println("Unable to create Lingo environment");
            return;
        }
    }
    static {
        //System.out.println(System.getProperty("java.library.path"));
        System.loadLibrary( "Lingj64_14");
    }

    public double procesar(){
        double dStatus[] = new double [1];
        double dfo[] = new double [1];
        int[] nPointersNow = new int[1];
        int nErr = lng.LSopenLogFileLng( pLngEnv, urlLog);
        if ( nErr != lng.LSERR_NO_ERROR_LNG )
        {
            System.out.println(urlLog);
            System.out.println( "LSopenLogFileLng() errore: " + nErr);
            return 0;
        }

        nErr = lng.LSsetPointerLng( pLngEnv, dStatus, nPointersNow);
        if ( nErr != lng.LSERR_NO_ERROR_LNG )
        {
            System.out.println( "***LSsetPointerLng() error***: " + nErr);
            return 0;
        }
        nErr = lng.LSsetPointerLng( pLngEnv, dfo, nPointersNow);
        if ( nErr != lng.LSERR_NO_ERROR_LNG )
        {
            System.out.println( "***LSsetPointerLng() error***: " + nErr);
            return 0;
        }
        // pass Lingo the name of the solver callback function...
        nErr = lng.LSsetCallbackSolverLng( pLngEnv, "MySolverCallback", this);
        if ( nErr != lng.LSERR_NO_ERROR_LNG )
        {
            System.out.println("LSsetCallbackSolverLng() error");
            return 0;
        }
        // ...and the error callback function
        nErr = lng.LSsetCallbackErrorLng( pLngEnv, "MyErrorCallback", this);
        if ( nErr != lng.LSERR_NO_ERROR_LNG )
        {
            System.out.println("LSsetCallbackErrorLng() error");
            return 0;
        }
        // echo input to log file
        String sScript = "SET ECHOIN 1" + "\n";

        // load the model from disk
        sScript = sScript + "TAKE "+ urlLingo + "\n";

        // view the formulation
        sScript = sScript + "LOOK ALL" + "\n";

        // solve
        sScript = sScript + "GO" + "\n";

        // exit script processor
        sScript = sScript + "QUIT" + "\n";
        dStatus[0] = -1;
        nLastIterationCount = -1;
        nErr = lng.LSexecuteScriptLng( pLngEnv, sScript);
        if ( nErr != lng.LSERR_NO_ERROR_LNG )
        {
            System.out.println( "***LSexecuteScriptLng error***: " + nErr);
            return 0;
        }
        // clear the pointers to force update of local arrays
        // ***NOTE*** solution won't get passed to local arrays until
        // LSclearPointersLng is called!!!
        nErr = lng.LSclearPointersLng( pLngEnv);
        if ( nErr != lng.LSERR_NO_ERROR_LNG )
        {
            System.out.println( "***LSclearPointerLng() error***: " + nErr);
            return 0;
        }


        // check the solution status
        if ( dStatus[0] != lng.LS_STATUS_GLOBAL_LNG)
            System.out.println( "***Unable to Solve*** dStatus:" + dStatus[0]);

        // close Lingo's log file
        nErr = lng.LScloseLogFileLng( pLngEnv);
        if ( nErr != lng.LSERR_NO_ERROR_LNG )
        {
            System.out.println( "***LScloseLogFileLng() error***: " + nErr);
            return 0;
        }
        return dfo[0];
    }



    private static int MySolverCallback( Object pnLng, int iLoc, Object jobj)
    {
        DemoLingo s = (DemoLingo) jobj;
        int nIterations[] = new int [1];
        s.lng.LSgetCallbackInfoIntLng( pnLng, Lingd14.LS_IINFO_ITERATIONS_LNG, nIterations);
        return( 0);
    }

    private static int MyErrorCallback( Object pnLng, int nErrorCode, String jsErrMessage, Object jobj)
    {
        System.out.println( "Lingo error code: " + nErrorCode);
        System.out.println("Lingo error message:\n\n " + jsErrMessage);
        return( 0);
    }
}
