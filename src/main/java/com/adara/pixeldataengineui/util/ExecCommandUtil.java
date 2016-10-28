package com.adara.pixeldataengineui.util;

import com.opinmind.util.Triplet;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ExecCommandUtil {
    public static void main(String[] args) throws Exception {
    /*
    * grep example on local:
    * String[] cmd = {"/bin/sh", "-c", "grep \"error parsing pixel data engine rule\" /opt/opinmind/logs/udcuv2/*"};
    * execWithOutput(null, cmd)
    * */

    /*
    * ls example on local:
    * String[] cmd = {"/bin/sh", "-c", "ls /Users/yzhao"}
    * execWithOutput(null, cmd)
    * */

        // //scp
//		String[] scpCmds = { "/usr/bin/scp", tmpFile, "manager" + ":" + tmpFile };
        // ExecUtil.exec(scpCmds);
        //
        // //move
//		String[] moveCmds = { "/usr/bin/ssh", "manager", "/bin/mv " + tmpFile + " " + finalDestFile  }; //would add /usr/bin/sudo for QA
        // ExecUtil.exec(moveCmds);
    }

    private static final Logger log = Logger.getLogger(ExecCommandUtil.class);

    public static String exec(String command) {
        return execWithOutput(command, null);
    }

    public static void execQuiet(String command) {
        execWithoutOutput(command, null);
    }

    public static void execWithoutOutput(String command, String[] commandArray) {
        log.info(String.format(
                "BEGIN execWithoutOutput(command='%s', commandArray=[%s])", command,
                Arrays.toString(commandArray)));
        try {
            Runtime rt = Runtime.getRuntime();
            Process proc = null;

            if (command == null && commandArray != null) {
                proc = rt.exec(commandArray);
            } else {
                proc = rt.exec(command);
            }
            int exitVal = proc.waitFor();
            log.info("Process exitValue: " + exitVal);
            proc.destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static String execWithOutput(String command, String[] commandArray) {
        log.info(String.format(
                "BEGIN execWithOutput(command='%s', commandArray=[%s])", command,
                Arrays.toString(commandArray)));

        try {
            Runtime rt = Runtime.getRuntime();
            Process proc = null;

            if (command == null && commandArray != null) {
                proc = rt.exec(commandArray);
            } else {
                proc = rt.exec(command);
            }

            InputStream stdin = proc.getInputStream();
            InputStreamReader isr = new InputStreamReader(stdin);
            BufferedReader br = new BufferedReader(isr);

            String all = "";
            String line = null;
            while ((line = br.readLine()) != null) {
                log.info(line);
                all += line + "\n";
            }

            InputStream stderr = proc.getErrorStream();
            InputStreamReader isr2 = new InputStreamReader(stderr);
            BufferedReader br2 = new BufferedReader(isr2);

            String line2 = null;
            while ((line2 = br2.readLine()) != null) {
                log.error(line2);
            }

            int exitVal = proc.waitFor();
            log.info("Process exitValue: " + exitVal);
            proc.destroy();
            return all;

        } catch (InterruptedException e) {

        } catch (Throwable t) {
            t.printStackTrace();
        }
        return null;
    }

    private static Triplet<Integer, String, String> execGeneralV2(
            String command, String[] commandArray) {
        log.info(String.format(
                "BEGIN execGeneralV2(command='%s', commandArray=[%s])",
                command, Arrays.toString(commandArray)));
        Triplet<Integer, String, String> result = null;
        try {
            Runtime rt = Runtime.getRuntime();
            Process proc = null;

            if (command == null && commandArray != null) {
                proc = rt.exec(commandArray);
            } else {
                proc = rt.exec(command);
            }

            InputStream stdin = proc.getInputStream();
            InputStreamReader isr = new InputStreamReader(stdin);
            BufferedReader br = new BufferedReader(isr);

            String all = "";
            String line = null;
            while ((line = br.readLine()) != null) {
                log.info(line);
                all += line + "\n";
            }

            InputStream stderr = proc.getErrorStream();
            InputStreamReader isr2 = new InputStreamReader(stderr);
            BufferedReader br2 = new BufferedReader(isr2);

            String line2 = null;
            StringBuffer error = new StringBuffer();
            while ((line2 = br2.readLine()) != null) {
                log.error(line2);
                error.append(line2);
            }

            int exitVal = proc.waitFor();
            result = new Triplet<Integer, String, String>(exitVal, all,
                    error.toString());
            log.info("Process exitValue: " + exitVal + ", result: " + all
                    + ", error: " + error.toString());
            proc.destroy();
            return result;

        } catch (Throwable t) {
            t.printStackTrace();
        }
        return null;
    }

    public static Triplet<Integer, String, String> execV2(String[] command) {
        return execGeneralV2(null, command);
    }

    public static String exec(String[] command) {
        return execWithOutput(null, command);
    }



}
