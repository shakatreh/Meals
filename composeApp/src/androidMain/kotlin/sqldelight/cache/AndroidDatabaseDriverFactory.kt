package sqldelight.cache

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.coding.meal.sqldelight.cache.AppDatabase
import com.coding.meal.sqldelight.cache.DatabaseDriverFactory

class AndroidDatabaseDriverFactory(private val context: Context) : DatabaseDriverFactory {
    override fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(AppDatabase.Schema, context, "launch.db")
    }
}