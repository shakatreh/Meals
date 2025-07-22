package sqldelight.cache

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.coding.meal.sqldelight.cache.AppDatabase
import com.coding.meal.sqldelight.cache.DatabaseDriverFactory

class IOSDatabaseDriverFactory : DatabaseDriverFactory {
    override fun createDriver(): SqlDriver {
        return NativeSqliteDriver(AppDatabase.Schema, "launch.db")
    }
}