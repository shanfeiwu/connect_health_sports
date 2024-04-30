/*
 * This file is auto-generated.  DO NOT MODIFY.
 */
package com.hnlens.ai.service;
// Declare any non-default types here with import statements
public interface ISportInterface extends android.os.IInterface
{
  /** Default implementation for ISportInterface. */
  public static class Default implements com.hnlens.ai.service.ISportInterface
  {
    @Override public java.lang.String getHealthData(byte healthType) throws android.os.RemoteException
    {
      return null;
    }
    @Override public java.lang.String readExerciseSessions(byte healthType) throws android.os.RemoteException
    {
      return null;
    }
    @Override public void writeExerciseSession(byte healthType) throws android.os.RemoteException
    {
    }
    @Override public void deleteExerciseSession(java.lang.String uid) throws android.os.RemoteException
    {
    }
    @Override public java.lang.String readAssociatedSessionData(java.lang.String uid) throws android.os.RemoteException
    {
      return null;
    }
    @Override
    public android.os.IBinder asBinder() {
      return null;
    }
  }
  /** Local-side IPC implementation stub class. */
  public static abstract class Stub extends android.os.Binder implements com.hnlens.ai.service.ISportInterface
  {
    /** Construct the stub at attach it to the interface. */
    public Stub()
    {
      this.attachInterface(this, DESCRIPTOR);
    }
    /**
     * Cast an IBinder object into an com.hnlens.ai.service.ISportInterface interface,
     * generating a proxy if needed.
     */
    public static com.hnlens.ai.service.ISportInterface asInterface(android.os.IBinder obj)
    {
      if ((obj==null)) {
        return null;
      }
      android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
      if (((iin!=null)&&(iin instanceof com.hnlens.ai.service.ISportInterface))) {
        return ((com.hnlens.ai.service.ISportInterface)iin);
      }
      return new com.hnlens.ai.service.ISportInterface.Stub.Proxy(obj);
    }
    @Override public android.os.IBinder asBinder()
    {
      return this;
    }
    @Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
    {
      java.lang.String descriptor = DESCRIPTOR;
      if (code >= android.os.IBinder.FIRST_CALL_TRANSACTION && code <= android.os.IBinder.LAST_CALL_TRANSACTION) {
        data.enforceInterface(descriptor);
      }
      switch (code)
      {
        case INTERFACE_TRANSACTION:
        {
          reply.writeString(descriptor);
          return true;
        }
      }
      switch (code)
      {
        case TRANSACTION_getHealthData:
        {
          byte _arg0;
          _arg0 = data.readByte();
          java.lang.String _result = this.getHealthData(_arg0);
          reply.writeNoException();
          reply.writeString(_result);
          break;
        }
        case TRANSACTION_readExerciseSessions:
        {
          byte _arg0;
          _arg0 = data.readByte();
          java.lang.String _result = this.readExerciseSessions(_arg0);
          reply.writeNoException();
          reply.writeString(_result);
          break;
        }
        case TRANSACTION_writeExerciseSession:
        {
          byte _arg0;
          _arg0 = data.readByte();
          this.writeExerciseSession(_arg0);
          reply.writeNoException();
          break;
        }
        case TRANSACTION_deleteExerciseSession:
        {
          java.lang.String _arg0;
          _arg0 = data.readString();
          this.deleteExerciseSession(_arg0);
          reply.writeNoException();
          break;
        }
        case TRANSACTION_readAssociatedSessionData:
        {
          java.lang.String _arg0;
          _arg0 = data.readString();
          java.lang.String _result = this.readAssociatedSessionData(_arg0);
          reply.writeNoException();
          reply.writeString(_result);
          break;
        }
        default:
        {
          return super.onTransact(code, data, reply, flags);
        }
      }
      return true;
    }
    private static class Proxy implements com.hnlens.ai.service.ISportInterface
    {
      private android.os.IBinder mRemote;
      Proxy(android.os.IBinder remote)
      {
        mRemote = remote;
      }
      @Override public android.os.IBinder asBinder()
      {
        return mRemote;
      }
      public java.lang.String getInterfaceDescriptor()
      {
        return DESCRIPTOR;
      }
      @Override public java.lang.String getHealthData(byte healthType) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        java.lang.String _result;
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeByte(healthType);
          boolean _status = mRemote.transact(Stub.TRANSACTION_getHealthData, _data, _reply, 0);
          _reply.readException();
          _result = _reply.readString();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
        return _result;
      }
      @Override public java.lang.String readExerciseSessions(byte healthType) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        java.lang.String _result;
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeByte(healthType);
          boolean _status = mRemote.transact(Stub.TRANSACTION_readExerciseSessions, _data, _reply, 0);
          _reply.readException();
          _result = _reply.readString();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
        return _result;
      }
      @Override public void writeExerciseSession(byte healthType) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeByte(healthType);
          boolean _status = mRemote.transact(Stub.TRANSACTION_writeExerciseSession, _data, _reply, 0);
          _reply.readException();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
      }
      @Override public void deleteExerciseSession(java.lang.String uid) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeString(uid);
          boolean _status = mRemote.transact(Stub.TRANSACTION_deleteExerciseSession, _data, _reply, 0);
          _reply.readException();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
      }
      @Override public java.lang.String readAssociatedSessionData(java.lang.String uid) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        java.lang.String _result;
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeString(uid);
          boolean _status = mRemote.transact(Stub.TRANSACTION_readAssociatedSessionData, _data, _reply, 0);
          _reply.readException();
          _result = _reply.readString();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
        return _result;
      }
    }
    static final int TRANSACTION_getHealthData = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
    static final int TRANSACTION_readExerciseSessions = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
    static final int TRANSACTION_writeExerciseSession = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
    static final int TRANSACTION_deleteExerciseSession = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
    static final int TRANSACTION_readAssociatedSessionData = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
  }
  public static final java.lang.String DESCRIPTOR = "com.hnlens.ai.service.ISportInterface";
  public java.lang.String getHealthData(byte healthType) throws android.os.RemoteException;
  public java.lang.String readExerciseSessions(byte healthType) throws android.os.RemoteException;
  public void writeExerciseSession(byte healthType) throws android.os.RemoteException;
  public void deleteExerciseSession(java.lang.String uid) throws android.os.RemoteException;
  public java.lang.String readAssociatedSessionData(java.lang.String uid) throws android.os.RemoteException;
  public static @interface HealthType {
    public static final byte STEP = 0;
    public static final byte WEIGHT = 1;
  }
}
