package com.example.cultura
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.runner.RunWith
@MediumTest
@RunWith(AndroidJUnit4::class)
class UserDaoTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var database: AppDatabase
    private lateinit var userDao: UserDao
    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        userDao = database.userDao()
    }
    @After
    fun teardown() {
        database.close()
    }
    @Test
    fun dodavanjeUsera_i_dohvatPoEmailu() = runBlocking {
        val user = User(
            email = "test@example.com",
            password = "lozinka123"
        )
        userDao.insertUser(user)
        val dohvateni = userDao.getUserByEmail("test@example.com")
        Assert.assertNotNull(dohvateni)
        Assert.assertEquals("test@example.com", dohvateni?.email)
        Assert.assertEquals("lozinka123", dohvateni?.password)
    }
}