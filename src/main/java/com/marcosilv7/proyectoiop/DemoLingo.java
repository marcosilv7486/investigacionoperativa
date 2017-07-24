package com.marcosilv7.proyectoiop;

import com.lindo.Lingd14;
public class DemoLingo {

    Lingd14 lng = new Lingd14();
    Object pLngEnv;
    int nLastIterationCount;

    // Load the Lingo JNI interface

    public DemoLingo(){
        pLngEnv = lng.LScreateEnvLng();
        if ( pLngEnv == null)
        {
            System.out.println("Unable to create Lingo environment");
            return;
        }
    }
    static {
        System.loadLibrary( "Lingj64_14");
    }

    public void procesar(){
        double dStatus[] = new double [1];
        int[] nPointersNow = new int[1];
        int nErr = lng.LSopenLogFileLng( pLngEnv, "C:\\Users\\wonde\\Desktop\\Repositorios\\investigacionoperativa\\log.txt");
        if ( nErr != lng.LSERR_NO_ERROR_LNG )
        {
            System.out.println( "LSopenLogFileLng() errore: " + nErr);
            return;
        }

        nErr = lng.LSsetPointerLng( pLngEnv, dStatus, nPointersNow);
        if ( nErr != lng.LSERR_NO_ERROR_LNG )
        {
            System.out.println( "***LSsetPointerLng() error***: " + nErr);
            return;
        }
        // pass Lingo the name of the solver callback function...
        nErr = lng.LSsetCallbackSolverLng( pLngEnv, "MySolverCallback", this);
        if ( nErr != lng.LSERR_NO_ERROR_LNG )
        {
            System.out.println("LSsetCallbackSolverLng() error");
            return;
        }
        // ...and the error callback function
        nErr = lng.LSsetCallbackErrorLng( pLngEnv, "MyErrorCallback", this);
        if ( nErr != lng.LSERR_NO_ERROR_LNG )
        {
            System.out.println("LSsetCallbackErrorLng() error");
            return;
        }
        // echo input to log file
        String sScript = "SET ECHOIN 1" + "\n";

        // load the model from disk
        sScript = sScript + "TAKE C:\\Users\\wonde\\Desktop\\Repositorios\\investigacionoperativa\\modelo.lng" + "\n";

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
            return;
        }
        // clear the pointers to force update of local arrays
        // ***NOTE*** solution won't get passed to local arrays until
        // LSclearPointersLng is called!!!
        nErr = lng.LSclearPointersLng( pLngEnv);
        if ( nErr != lng.LSERR_NO_ERROR_LNG )
        {
            System.out.println( "***LSclearPointerLng() error***: " + nErr);
            return;
        }


        // check the solution status
        if ( dStatus[0] != lng.LS_STATUS_GLOBAL_LNG)
            System.out.println( "***Unable to Solve*** dStatus:" + dStatus[0]);

        // close Lingo's log file
        nErr = lng.LScloseLogFileLng( pLngEnv);
        if ( nErr != lng.LSERR_NO_ERROR_LNG )
        {
            System.out.println( "***LScloseLogFileLng() error***: " + nErr);
            return;
        }
    }

    public static void main(String args[]) {

        DemoLingo obj=new DemoLingo();
        obj.procesar();
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
