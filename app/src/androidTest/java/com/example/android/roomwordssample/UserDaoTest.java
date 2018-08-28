package com.example.android.roomwordssample;

/*
 * Copyright (C) 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.android.roomwordssample.data.entity.User;
import com.example.android.roomwordssample.data.dao.UserDao;
import com.example.android.roomwordssample.data.database.UserRoomDatabase;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;


/**
 * This is not meant to be a full set of tests. For simplicity, most of your samples do not
 * include tests. However, when building the Room, it is helpful to make sure it works before
 * adding the UI.
 */

@RunWith(AndroidJUnit4.class)
public class UserDaoTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private UserDao mUserDao;
    private UserRoomDatabase mDb;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getTargetContext();
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        mDb = Room.inMemoryDatabaseBuilder(context, UserRoomDatabase.class)
                // Allowing main thread queries, just for testing.
                .allowMainThreadQueries()
                .build();
        mUserDao = mDb.wordDao();
    }

    @After
    public void closeDb() throws IOException {
        mDb.close();
    }

    @Test
    public void insertAndGetWord() throws Exception {
        User user = new User("user");
        mUserDao.insert(user);
        List<User> allUsers = LiveDataTestUtil.getValue(mUserDao.getAlphabetizedWords());
        assertEquals(allUsers.get(0).getWord(), user.getWord());
    }

    @Test
    public void getAllWords() throws Exception {
        User user = new User("aaa");
        mUserDao.insert(user);
        User user2 = new User("bbb");
        mUserDao.insert(user2);
        List<User> allUsers = LiveDataTestUtil.getValue(mUserDao.getAlphabetizedWords());
        assertEquals(allUsers.get(0).getWord(), user.getWord());
        assertEquals(allUsers.get(1).getWord(), user2.getWord());
    }

    @Test
    public void deleteAll() throws Exception {
        User user = new User("user");
        mUserDao.insert(user);
        User user2 = new User("user2");
        mUserDao.insert(user2);
        mUserDao.deleteAll();
        List<User> allUsers = LiveDataTestUtil.getValue(mUserDao.getAlphabetizedWords());
        assertTrue(allUsers.isEmpty());
    }
}
