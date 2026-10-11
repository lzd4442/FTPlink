package com.ftplink.data.local;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class ServerDao_Impl implements ServerDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ServerEntity> __insertionAdapterOfServerEntity;

  private final EntityDeletionOrUpdateAdapter<ServerEntity> __deletionAdapterOfServerEntity;

  private final EntityDeletionOrUpdateAdapter<ServerEntity> __updateAdapterOfServerEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteServerById;

  public ServerDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfServerEntity = new EntityInsertionAdapter<ServerEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `servers` (`id`,`name`,`host`,`port`,`username`,`password`,`type`,`lastPath`,`createdAt`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ServerEntity entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
        }
        if (entity.getHost() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getHost());
        }
        statement.bindLong(4, entity.getPort());
        if (entity.getUsername() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getUsername());
        }
        if (entity.getPassword() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getPassword());
        }
        if (entity.getType() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getType());
        }
        if (entity.getLastPath() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getLastPath());
        }
        statement.bindLong(9, entity.getCreatedAt());
      }
    };
    this.__deletionAdapterOfServerEntity = new EntityDeletionOrUpdateAdapter<ServerEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `servers` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ServerEntity entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfServerEntity = new EntityDeletionOrUpdateAdapter<ServerEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `servers` SET `id` = ?,`name` = ?,`host` = ?,`port` = ?,`username` = ?,`password` = ?,`type` = ?,`lastPath` = ?,`createdAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ServerEntity entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
        }
        if (entity.getHost() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getHost());
        }
        statement.bindLong(4, entity.getPort());
        if (entity.getUsername() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getUsername());
        }
        if (entity.getPassword() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getPassword());
        }
        if (entity.getType() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getType());
        }
        if (entity.getLastPath() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getLastPath());
        }
        statement.bindLong(9, entity.getCreatedAt());
        statement.bindLong(10, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteServerById = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM servers WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertServer(final ServerEntity server,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfServerEntity.insertAndReturnId(server);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteServer(final ServerEntity server,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfServerEntity.handle(server);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateServer(final ServerEntity server,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfServerEntity.handle(server);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteServerById(final long id, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteServerById.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, id);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteServerById.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<ServerEntity>> getAllServers() {
    final String _sql = "SELECT * FROM servers ORDER BY createdAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"servers"}, new Callable<List<ServerEntity>>() {
      @Override
      @NonNull
      public List<ServerEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfHost = CursorUtil.getColumnIndexOrThrow(_cursor, "host");
          final int _cursorIndexOfPort = CursorUtil.getColumnIndexOrThrow(_cursor, "port");
          final int _cursorIndexOfUsername = CursorUtil.getColumnIndexOrThrow(_cursor, "username");
          final int _cursorIndexOfPassword = CursorUtil.getColumnIndexOrThrow(_cursor, "password");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfLastPath = CursorUtil.getColumnIndexOrThrow(_cursor, "lastPath");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<ServerEntity> _result = new ArrayList<ServerEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ServerEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpHost;
            if (_cursor.isNull(_cursorIndexOfHost)) {
              _tmpHost = null;
            } else {
              _tmpHost = _cursor.getString(_cursorIndexOfHost);
            }
            final int _tmpPort;
            _tmpPort = _cursor.getInt(_cursorIndexOfPort);
            final String _tmpUsername;
            if (_cursor.isNull(_cursorIndexOfUsername)) {
              _tmpUsername = null;
            } else {
              _tmpUsername = _cursor.getString(_cursorIndexOfUsername);
            }
            final String _tmpPassword;
            if (_cursor.isNull(_cursorIndexOfPassword)) {
              _tmpPassword = null;
            } else {
              _tmpPassword = _cursor.getString(_cursorIndexOfPassword);
            }
            final String _tmpType;
            if (_cursor.isNull(_cursorIndexOfType)) {
              _tmpType = null;
            } else {
              _tmpType = _cursor.getString(_cursorIndexOfType);
            }
            final String _tmpLastPath;
            if (_cursor.isNull(_cursorIndexOfLastPath)) {
              _tmpLastPath = null;
            } else {
              _tmpLastPath = _cursor.getString(_cursorIndexOfLastPath);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new ServerEntity(_tmpId,_tmpName,_tmpHost,_tmpPort,_tmpUsername,_tmpPassword,_tmpType,_tmpLastPath,_tmpCreatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getServerById(final long id, final Continuation<? super ServerEntity> $completion) {
    final String _sql = "SELECT * FROM servers WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<ServerEntity>() {
      @Override
      @Nullable
      public ServerEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfHost = CursorUtil.getColumnIndexOrThrow(_cursor, "host");
          final int _cursorIndexOfPort = CursorUtil.getColumnIndexOrThrow(_cursor, "port");
          final int _cursorIndexOfUsername = CursorUtil.getColumnIndexOrThrow(_cursor, "username");
          final int _cursorIndexOfPassword = CursorUtil.getColumnIndexOrThrow(_cursor, "password");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfLastPath = CursorUtil.getColumnIndexOrThrow(_cursor, "lastPath");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final ServerEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpHost;
            if (_cursor.isNull(_cursorIndexOfHost)) {
              _tmpHost = null;
            } else {
              _tmpHost = _cursor.getString(_cursorIndexOfHost);
            }
            final int _tmpPort;
            _tmpPort = _cursor.getInt(_cursorIndexOfPort);
            final String _tmpUsername;
            if (_cursor.isNull(_cursorIndexOfUsername)) {
              _tmpUsername = null;
            } else {
              _tmpUsername = _cursor.getString(_cursorIndexOfUsername);
            }
            final String _tmpPassword;
            if (_cursor.isNull(_cursorIndexOfPassword)) {
              _tmpPassword = null;
            } else {
              _tmpPassword = _cursor.getString(_cursorIndexOfPassword);
            }
            final String _tmpType;
            if (_cursor.isNull(_cursorIndexOfType)) {
              _tmpType = null;
            } else {
              _tmpType = _cursor.getString(_cursorIndexOfType);
            }
            final String _tmpLastPath;
            if (_cursor.isNull(_cursorIndexOfLastPath)) {
              _tmpLastPath = null;
            } else {
              _tmpLastPath = _cursor.getString(_cursorIndexOfLastPath);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _result = new ServerEntity(_tmpId,_tmpName,_tmpHost,_tmpPort,_tmpUsername,_tmpPassword,_tmpType,_tmpLastPath,_tmpCreatedAt);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
