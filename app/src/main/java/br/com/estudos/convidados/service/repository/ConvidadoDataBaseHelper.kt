package br.com.estudos.convidados.service.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import br.com.estudos.convidados.service.constants.DataBaseConstants

class ConvidadoDataBaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_CONVIDADOS)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "Convidados.db"

        private const val CREATE_TABLE_CONVIDADOS =
            ("create table " + DataBaseConstants.CONVIDADO.TABLE_NAME + " ("
                    + DataBaseConstants.CONVIDADO.COLUMNS.ID + " integer primary key autoincrement, "
                    + DataBaseConstants.CONVIDADO.COLUMNS.NAME + " text, "
                    + DataBaseConstants.CONVIDADO.COLUMNS.PRESENCE + " integer);")

    }
}