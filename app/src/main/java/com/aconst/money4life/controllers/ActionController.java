package com.aconst.money4life.controllers;

import com.aconst.money4life.model.Actions;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.functions.Action;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class ActionController {
    private RealmConfiguration realmConfiguration;

    public ActionController() {
        realmConfiguration = Realm.getDefaultConfiguration();
    }

    public Single<List<Actions>> getActions() {
        Realm realm = Realm.getInstance(realmConfiguration);
        List<Actions> actions = realm.copyFromRealm(realm.where(Actions.class).findAll());
        realm.close();
        return Single.just(actions);
    }

    public Single<Actions> getAction(long id) {
        Realm realm = Realm.getInstance(realmConfiguration);
        Actions action = null;
        Actions realmAction = realm.where(Actions.class).equalTo("id", id).findFirst();
        if (realmAction != null) {
            action = realm.copyFromRealm(realmAction);
        }
        realm.close();
        return Single.just(action);
    }

    public Completable updateActions(final List<Actions> actions) {
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                Realm realm = Realm.getInstance(realmConfiguration);
                realm.insertOrUpdate(actions);
                realm.close();
                Realm.compactRealm(realmConfiguration);
            }
        });
    }

    public Completable updateAction(final Actions action) {
        return Completable.fromAction(new Action() {
          @Override
          public void run() throws Exception {
              Realm realm = Realm.getInstance(realmConfiguration);
              realm.insertOrUpdate(action);
              realm.close();
              Realm.compactRealm(realmConfiguration);
          }
      });
    }
}
