package br.com.estudos.convidados.service.repository

import android.content.ContentValues
import android.content.Context
import br.com.estudos.convidados.service.constants.DataBaseConstants
import br.com.estudos.convidados.service.model.ConvidadoModel
import java.lang.Exception

class ConvidadoRepository private constructor(context: Context) {

    private var mConvidadoDataBaseHelper: ConvidadoDataBaseHelper = ConvidadoDataBaseHelper(context)

    companion object {
        private lateinit var repository: ConvidadoRepository

        fun getInstance(context: Context): ConvidadoRepository {
            if (!::repository.isInitialized) {
                repository = ConvidadoRepository(context)
            }
            return repository
        }
    }

    fun get(id: Int) : ConvidadoModel? {

        var convidado: ConvidadoModel? = null
        return try {
            val db = mConvidadoDataBaseHelper.readableDatabase

            // forma mais simples de fazer porém com mais risco de errar
           // db.rawQuery("select * from Convidado where id = $id", null)

            val projection = arrayOf(DataBaseConstants.CONVIDADO.COLUMNS.NAME, DataBaseConstants.CONVIDADO.COLUMNS.PRESENCE)
            val selection = DataBaseConstants.CONVIDADO.COLUMNS.ID + " = ?"
            val args = arrayOf(id.toString())

           val cursor = db.query(
               DataBaseConstants.CONVIDADO.TABLE_NAME,
            projection,
            selection,
            args,
            null,
            null,
            null
           )

            if (cursor != null && cursor.count > 0) {
                cursor.moveToFirst()

              val name = cursor.getString(cursor.getColumnIndex(DataBaseConstants.CONVIDADO.COLUMNS.NAME))
              val presence = (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.CONVIDADO.COLUMNS.PRESENCE)) == 1)

               convidado = ConvidadoModel(id, name, presence)
            }

            cursor.close()

            convidado
        } catch (e: Exception) {
            convidado
        }
    }


    fun getTodosConvidados(): List<ConvidadoModel> {
        val list: MutableList<ConvidadoModel> = ArrayList()
        return try {
            val db = mConvidadoDataBaseHelper.readableDatabase

            // forma mais simples de fazer porém com mais risco de errar
            // db.rawQuery("select * from Convidado where id = $id", null)

            val projection = arrayOf(
                DataBaseConstants.CONVIDADO.COLUMNS.ID,
                DataBaseConstants.CONVIDADO.COLUMNS.NAME,
                DataBaseConstants.CONVIDADO.COLUMNS.PRESENCE)



            val cursor = db.query(
                DataBaseConstants.CONVIDADO.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
            )

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.CONVIDADO.COLUMNS.ID))
                    val name = cursor.getString(cursor.getColumnIndex(DataBaseConstants.CONVIDADO.COLUMNS.NAME))
                    val presence = (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.CONVIDADO.COLUMNS.PRESENCE)) == 1)

                    val convidado = ConvidadoModel(id, name, presence)
                    list.add(convidado)
                }


            }

            cursor.close()

            list
        } catch (e: Exception) {
            list
        }

    }

    fun getPresente(): List<ConvidadoModel> {

        val list: MutableList<ConvidadoModel> = ArrayList()

        return try {
            val db = mConvidadoDataBaseHelper.readableDatabase

            // forma mais simples de fazer porém com mais risco de errar
            // db.rawQuery("select * from Convidado where id = $id", null)


            val cursor = db.rawQuery("SELECT id, name, presence FROM Convidado WHERE presence = 1", null)

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.CONVIDADO.COLUMNS.ID))
                    val name = cursor.getString(cursor.getColumnIndex(DataBaseConstants.CONVIDADO.COLUMNS.NAME))
                    val presence = (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.CONVIDADO.COLUMNS.PRESENCE)) == 1)

                    val convidado = ConvidadoModel(id, name, presence)
                    list.add(convidado)
                }


            }

            cursor.close()

            list
        } catch (e: Exception) {
            list
        }

    }

    fun getAusente(): List<ConvidadoModel> {
        val list: MutableList<ConvidadoModel> = ArrayList()
        return try {
            val db = mConvidadoDataBaseHelper.readableDatabase

            val cursor = db.rawQuery("SELECT id, name, presence FROM Convidado WHERE presence = 0", null)

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.CONVIDADO.COLUMNS.ID))
                    val name = cursor.getString(cursor.getColumnIndex(DataBaseConstants.CONVIDADO.COLUMNS.NAME))
                    val presence = (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.CONVIDADO.COLUMNS.PRESENCE)) == 1)

                    val convidado = ConvidadoModel(id, name, presence)
                    list.add(convidado)
                }


            }

            cursor.close()

            list
        } catch (e: Exception) {
            list
        }
    }

    fun save(convidado: ConvidadoModel): Boolean {
        return try {
            val db = mConvidadoDataBaseHelper.writableDatabase

            val value = ContentValues()
            value.put(DataBaseConstants.CONVIDADO.COLUMNS.NAME, convidado.nome)
            value.put(DataBaseConstants.CONVIDADO.COLUMNS.PRESENCE, convidado.presence)
            db.insert(DataBaseConstants.CONVIDADO.TABLE_NAME, null, value)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun update(convidado: ConvidadoModel): Boolean {
        return try {
            val db = mConvidadoDataBaseHelper.writableDatabase

            val value = ContentValues()
            value.put(DataBaseConstants.CONVIDADO.COLUMNS.NAME, convidado.nome)
            value.put(DataBaseConstants.CONVIDADO.COLUMNS.PRESENCE, convidado.presence)

            val selection = DataBaseConstants.CONVIDADO.COLUMNS.ID + " = ?"
            val args = arrayOf(convidado.id.toString())

            db.update(DataBaseConstants.CONVIDADO.TABLE_NAME, value, selection, args)
            true
        } catch (e: Exception) {
            false
        }

    }

    fun delete(id: Int): Boolean {
        return try {
            val db = mConvidadoDataBaseHelper.writableDatabase

            val selection = DataBaseConstants.CONVIDADO.COLUMNS.ID + " = ?"
            val args = arrayOf(id.toString())

            db.delete(DataBaseConstants.CONVIDADO.TABLE_NAME, selection, args)
            true
        } catch (e: Exception) {
            false
        }

    }
}
