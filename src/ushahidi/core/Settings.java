/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ushahidi.core;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Vector;
import javax.microedition.rms.InvalidRecordIDException;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;
import javax.microedition.rms.RecordStoreNotOpenException;

/**
 *
 * @author stuart
 */
public class Settings {

    private RecordStore getRecordStore(String recordStore) {
        RecordStore store = null;
        try {
            store = RecordStore.openRecordStore(recordStore, true);
        }
        catch (RecordStoreException e) {
            System.err.println(e.getMessage());
        }
        return store;
    }



    public int saveDeployment(String deployment)
    {
        RecordStore rs = null;
        int recordID = 0;
        try
        {
            rs = getRecordStore("DeployDB");
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            DataOutputStream writer = new DataOutputStream(byteStream);
            writer.writeUTF(deployment);
            writer.flush();

            byte[] record = byteStream.toByteArray();
            if (rs.getNumRecords() == 0)
                recordID = rs.addRecord(record, 0, record.length);
            else rs.setRecord(1, record, 0, record.length);

            writer.close();
            byteStream.close();
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
        }
        finally
        {
            closeRecordStore(rs);
        }
        return recordID;
    }
    public int saveSettings(String firstName, String lastName, String email) {
        RecordStore rs = null;
        int recordID = 0;
        try {
            rs = getRecordStore("SettingsDB");
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            DataOutputStream writer = new DataOutputStream(byteStream);
            writer.writeUTF(firstName); // First name
            writer.writeUTF(lastName); //Last Name
            writer.writeUTF(email); // E-mail address
            writer.flush();

            byte[] record = byteStream.toByteArray();
            if (rs.getNumRecords() == 0)
                recordID = rs.addRecord(record, 0, record.length);
            else rs.setRecord(1, record, 0, record.length);

            writer.close();
            byteStream.close();
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
        finally {
            closeRecordStore(rs);
        }
        return recordID;
    }

    public String[] getDeployment()
    {
        ByteArrayInputStream inputByteStream;
        DataInputStream reader;
        String[] deployments = null;
        try
        {
            RecordStore rs = getRecordStore("DeployDB");

            try
            {
                byte[] settings = rs.getRecord(1);
                inputByteStream = new ByteArrayInputStream(settings);
                reader = new DataInputStream(inputByteStream);

                if(rs.getNumRecords() != 0)
                {
                    deployments = new String[1];

                    deployments[0] = reader.readUTF();
                }
            }
            catch (InvalidRecordIDException ex) {
                System.err.println(ex.getMessage());
            }
        }
        catch (RecordStoreNotOpenException ex) {
            System.err.println(ex);
        }
        catch (Exception ex) {
            System.err.println(ex);
        }
        return deployments;
    }
    
    public String[] getSettings() {
        ByteArrayInputStream inputByteStream;
        DataInputStream reader;
        String[] userSetting = null;
        try {
            RecordStore rs = getRecordStore("SettingsDB");

            try {
                byte[] settings = rs.getRecord(1);
                inputByteStream = new ByteArrayInputStream(settings);
                reader = new DataInputStream(inputByteStream);

                if (rs.getNumRecords()  != 0) {
                    userSetting = new String[3];

                    userSetting[0] = reader.readUTF(); // First name
                    userSetting[1] = reader.readUTF(); //Last Name
                    userSetting[2] = reader.readUTF(); // E-mail address
                } //end if

            }
            catch (InvalidRecordIDException ex) {
                System.err.println(ex.getMessage());
            }
        }
        catch (RecordStoreNotOpenException ex) {
            System.err.println(ex);
        }
        catch (Exception ex) {
            System.err.println(ex);
        }
        return userSetting;
    }

    public Vector getAllInstances() {
        Vector instances = new Vector();
//        String activeInstance = null;
        RecordStore rs = null;
        ByteArrayInputStream byteStream = null;
        DataInputStream reader = null;

        try {
            rs = getRecordStore("InstancesDB");

                for (int i = 1; i <= rs.getNumRecords(); i++) {
                    byte[] data = rs.getRecord(i);
                    byteStream = new ByteArrayInputStream(data);
                    reader = new DataInputStream(byteStream);

                    String deploymentName = reader.readUTF();
                    String activeInstance = reader.readUTF();

                    instances.addElement(new String[]{deploymentName, activeInstance});

                    // Close streams
                    reader.close();
                    byteStream.close();
                } // end for

        } catch (Exception e) {
            System.err.println(e);
        }

        return instances;
    }

    public String[] getTitles() {
        String[] titles = null;
        Vector instancesVector = getAllInstances();

        titles = new String[instancesVector.size()];

        for (int i = 0; i < instancesVector.size(); i++) {
            String instance[] = (String[]) instancesVector.elementAt(i);
            titles[i] = instance[0];
        }

        return titles;
    }

    private void closeRecordStore(RecordStore recordStore) {
        if (recordStore != null) {
            try {
                recordStore.closeRecordStore();
            }
            catch (RecordStoreException e) {
                System.err.println(e.getMessage());
            }
        } //end if
    }
}