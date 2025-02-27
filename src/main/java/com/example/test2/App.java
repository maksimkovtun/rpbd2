package com.example.test2;

import com.example.test2.DAO.*;
import com.example.test2.Hibernate.DatabaseConfig;
import com.example.test2.Hibernate.HibernateUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.Objects;

public class App extends Application {

        private static Session session;
        private SessionFactory sessionFactory;

        @Override
        public void start(Stage stage) throws IOException, URISyntaxException {
            DatabaseConfig config = HibernateUtil.getConfig();

            if (config != null) {
                try {
                session = null;
                session = HibernateUtil.getSessionFactory(config).openSession();
                }catch(Exception e){
                    e.printStackTrace();
                    showAlert("Ошибка подключения к базе данных", "Не удалось установить соединение с базой данных.");
                }
                FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("App.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                AppController controller = fxmlLoader.getController();
                stage.setResizable(false);

                stage.setTitle("Школа");
                Image iconImage = new Image(Objects.requireNonNull(App.class.getResource("images/icon.png")).toURI().toString());
                stage.getIcons().add(iconImage);
                stage.setScene(scene);
                stage.show();
            } else {
                System.out.println("Отмена ввода данных. Приложение закрывается.");
                stage.close();
            }
        }
    public static Session getSession() {
           return session;
        }

    public static void addSchoolboy(String pSurname, String pFirstname, String Surname, String FirstName, String Surname2, String addressName, int BirthDate, String className) {
            session = getSession();
            int ClNewId = 0;
            int newPId = 0;
            int newAId = 0;
            int newRId = 0;
            int AdmYear = 0;
            Calendar calendar = Calendar.getInstance();
            String digitsOnly = className.replaceAll("[^0-9]", "");
            int integerValue = Integer.parseInt(digitsOnly);
            AdmYear = calendar.get(Calendar.YEAR) + (11-integerValue);
            ClNewId = searchClassName(className);
            newAId = addAddress(addressName);
            newPId = addParant(pSurname,pFirstname,"",newAId,"");
            newRId = addRating(getMaxSchoolboyId(), 0,0,0,0,0,0);
        try {
            session.beginTransaction();
            Query query = session.createQuery("SELECT MAX(s.sId) FROM SchoolboyEntity s");
            Integer maxSId = (Integer) query.uniqueResult();
            int newSId = (maxSId != null) ? maxSId + 1 : 1;

            SchoolboyEntity schoolboyEntity = new SchoolboyEntity();

            schoolboyEntity.setsId(newSId);
            schoolboyEntity.setsPId(newPId);
            schoolboyEntity.setsRId(newRId);
            schoolboyEntity.setsSurname(Surname);
            schoolboyEntity.setsFirstName(FirstName);
            schoolboyEntity.setsSurname2(Surname2);
            schoolboyEntity.setsAddressId(newAId);
            schoolboyEntity.setsBirthDate(BirthDate);
            schoolboyEntity.setsClassId(ClNewId);
            schoolboyEntity.setsAdmYear(AdmYear);

            session.save(schoolboyEntity);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        }
    }
    public static int getMaxSchoolboyId(){
        session = getSession();
        int newId = 0;
        try {
            session.beginTransaction();
            Query query = session.createQuery("SELECT MAX(s.sId) FROM SchoolboyEntity s");
            Integer maxSId = (Integer) query.uniqueResult();
            newId = (maxSId != null) ? maxSId + 1 : 1;
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        }
            return newId;
    }
    public static int addParant(String pSurname, String pFirstName, String pSurname2, int pAddressId, String pStatus) {
        session = getSession();
        int newIdP = 0;
        try {
            session.beginTransaction();

            Query queryP = session.createQuery("SELECT MAX(s.pId) FROM ParantsEntity s");
            Integer maxIdP = (Integer) queryP.uniqueResult();

            newIdP = (maxIdP != null) ? maxIdP + 1 : 1;

            ParantsEntity contactEntity = new ParantsEntity();
            contactEntity.setpId(newIdP);
            contactEntity.setpSurname(pSurname);
            contactEntity.setpFirstName(pFirstName);
            contactEntity.setpSurname2(pSurname2);
            contactEntity.setpAddressId(pAddressId);
            contactEntity.setpStatus(pStatus);

            session.save(contactEntity);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        }
        return newIdP;
    }
    public static int addAddress(String adName) {
        session = getSession();
        int newIdA = 0;
        try {
            session.beginTransaction();

            Query queryA = session.createQuery("SELECT MAX(s.adId) FROM AddressEntity s");
            Integer maxIdA = (Integer) queryA.uniqueResult();

            newIdA = (maxIdA != null) ? maxIdA + 1 : 1;

            AddressEntity contactEntity = new AddressEntity();
            contactEntity.setAdId(newIdA);
            contactEntity.setAdName(adName);

            session.save(contactEntity);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        }
        return newIdA;
    }

    public static void addClass(String clName) {
        session = getSession();
        try {
            session.beginTransaction();

            Query queryCl = session.createQuery("SELECT MAX(s.clId) FROM ClassEntity s");
            Integer maxIdCl = (Integer) queryCl.uniqueResult();

            int newIdCl = (maxIdCl != null) ? maxIdCl + 1 : 1;
            System.out.println(newIdCl);

            ClassEntity contactEntity = new ClassEntity();
            contactEntity.setClId(newIdCl);
            contactEntity.setClName(clName);

            session.save(contactEntity);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        }
    }
    public static int addRating(int rClassId, int rSubjectId, int rPointQuarter, int rPointPartYear, int rPointYear, int rPointExam, int rPointEnd) {
        session = getSession();
        int newIdR = 0;
        int rYear = 0;
        Calendar calendar = Calendar.getInstance();
        rYear = calendar.get(Calendar.YEAR);
        try {
            session.beginTransaction();

            Query queryR = session.createQuery("SELECT MAX(s.rId) FROM RatingEntity s");
            Integer maxIdR = (Integer) queryR.uniqueResult();

            newIdR = (maxIdR != null) ? maxIdR + 1 : 1;

            RatingEntity contactEntity = new RatingEntity();
            contactEntity.setrId(newIdR);
            contactEntity.setrYear(rYear);
            contactEntity.setrClassId(rClassId);
            contactEntity.setrSubjectId(rSubjectId);
            contactEntity.setrPointQuarter(rPointQuarter);
            contactEntity.setrPointPartYear(rPointPartYear);
            contactEntity.setrPointYear(rPointYear);
            contactEntity.setrPointExam(rPointExam);
            contactEntity.setrPointEnd(rPointEnd);

            session.save(contactEntity);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        }
        return newIdR;
    }
    public static void addSubject(String suName) {
        session = getSession();
        try {
            session.beginTransaction();

            Query querySu = session.createQuery("SELECT MAX(s.suId) FROM SubjectEntity s");
            Integer maxIdSu = (Integer) querySu.uniqueResult();

            int newIdSu = (maxIdSu != null) ? maxIdSu + 1 : 1;
            System.out.println(newIdSu);

            SubjectEntity contactEntity = new SubjectEntity();
            contactEntity.setSuId(newIdSu);
            contactEntity.setSuName(suName);

            session.save(contactEntity);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        }
    }

    //Удаление
    public static void delSchoolboy(int sId) {
        session = getSession();
        try {
            session.beginTransaction();

            SchoolboyEntity schoolboy = session.get(SchoolboyEntity.class, sId);
            if (schoolboy != null) {
                session.delete(schoolboy);
                session.getTransaction().commit();
            } else {
                System.out.println("Schoolboy with ID " + sId + " not found.");
            }
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        }
    }
    public static void delParant(int pId) {
        session = getSession();
        try {
            session.beginTransaction();

            ParantsEntity parant = session.get(ParantsEntity.class, pId);
            if (parant != null) {
                session.delete(parant);
                session.getTransaction().commit();
            } else {
                System.out.println("Parent with ID " + pId + " not found.");
            }
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        }
    }
    public static void delAddress(int aId) {
        session = getSession();
        try {
            session.beginTransaction();

            AddressEntity address = session.get(AddressEntity.class, aId);
            if (address != null) {
                session.delete(address);
                session.getTransaction().commit();
            } else {
                System.out.println("Address with ID " + aId + " not found.");
            }
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        }
    }
    public static void delClass(int clId) {
        session = getSession();
        try {
            session.beginTransaction();

            ClassEntity class_ = session.get(ClassEntity.class, clId);
            if (class_ != null) {
                session.delete(class_);
                session.getTransaction().commit();
            } else {
                System.out.println("Class with ID " + clId + " not found.");
            }
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        }
    }
    public static void delRating(int rId) {
        session = getSession();
        try {
            session.beginTransaction();

            RatingEntity rating = session.get(RatingEntity.class, rId);
            if (rating != null) {
                session.delete(rating);
                session.getTransaction().commit();
            } else {
                System.out.println("Rating with ID " + rId + " not found.");
            }
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        }
    }
    public static void delSubject(int suId) {
        session = getSession();
        try {
            session.beginTransaction();

            SubjectEntity subject = session.get(SubjectEntity.class, suId);
            if (subject != null) {
                session.delete(subject);
                session.getTransaction().commit();
            } else {
                System.out.println("Subject with ID " + suId + " not found.");
            }
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        }
    }

    //Обновление
    public static void updSchoolboy(int sId, int PId, int RId, String Surname, String FirstName, String Surname2, int AddressId, int BirthDate, int ClassId, int AdmYear) {
        session = getSession();
        try {
            session.beginTransaction();

            SchoolboyEntity schoolboy = session.get(SchoolboyEntity.class, sId);

            if(schoolboy != null) {
                schoolboy.setsPId(PId);
                schoolboy.setsRId(RId);
                schoolboy.setsSurname(Surname);
                schoolboy.setsFirstName(FirstName);
                schoolboy.setsSurname2(Surname2);
                schoolboy.setsAddressId(AddressId);
                schoolboy.setsBirthDate(BirthDate);
                schoolboy.setsClassId(ClassId);
                schoolboy.setsAdmYear(AdmYear);

                session.save(schoolboy);
                session.getTransaction().commit();
            } else {
            System.out.println("Schoolboy with ID " + sId + " not found.");
        }
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        }
    }

    public static void updParant(int pId, String pFirstName, String pSurname, String pSurname2, int pAddressId, String pStatus) {
        session = getSession();
        try {
            session.beginTransaction();

            ParantsEntity parant = session.get(ParantsEntity.class, pId);

            if(parant != null) {
            parant.setpSurname(pSurname);
            parant.setpFirstName(pFirstName);
            parant.setpSurname2(pSurname2);
            parant.setpAddressId(pAddressId);
            parant.setpStatus(pStatus);

            session.save(parant);
            session.getTransaction().commit();
            } else {
                System.out.println("Parent with ID " + pId + " not found.");
            }
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        }
    }
    public static void updAddress(int aId, String adName) {
        session = getSession();
        try {
            session.beginTransaction();

            AddressEntity address = session.get(AddressEntity.class, aId);

            if(address != null) {
                address.setAdName(adName);

                session.save(address);
                session.getTransaction().commit();
            } else {
                System.out.println("Address with ID " + aId + " not found.");
        }
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        }
    }
    public static void updClass(int clId, String clName) {
        session = getSession();
        try {
            session.beginTransaction();

            ClassEntity class_ = session.get(ClassEntity.class, clId);

            if(class_ != null) {
                class_.setClName(clName);

            session.save(class_);
            session.getTransaction().commit();
            } else {
                System.out.println("Class with ID " + clId + " not found.");
            }
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        }
    }
    public static void updRating(int rId, int rYear, int rClassId, int rSubjectId, int rPointQuarter, int rPointPartYear, int rPointYear, int rPointExam, int rPointEnd) {
        session = getSession();
        try {
            session.beginTransaction();

            RatingEntity rating = session.get(RatingEntity.class, rId);

            if(rating != null) {
            rating.setrYear(rYear);
            rating.setrClassId(rClassId);
            rating.setrSubjectId(rSubjectId);
            rating.setrPointQuarter(rPointQuarter);
            rating.setrPointPartYear(rPointPartYear);
            rating.setrPointYear(rPointYear);
            rating.setrPointExam(rPointExam);
            rating.setrPointEnd(rPointEnd);

            session.save(rating);
            session.getTransaction().commit();
            } else {
                System.out.println("Rating with ID " + rId + " not found.");
            }
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        }
    }
    public static void updSubject(int suId, String suName) {
        session = getSession();
        try {
            session.beginTransaction();

            SubjectEntity subject = session.get(SubjectEntity.class, suId);

            if(subject != null) {
            subject.setSuName(suName);

            session.save(subject);
            session.getTransaction().commit();
            } else {
                System.out.println("Subject with ID " + suId + " not found.");
            }
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        }
    }
    //Обновление по полям
    public static void updateField(int tableNumber, String fieldName, int fieldId, String newField){
        session = getSession();
        try {
            session.beginTransaction();
            if(tableNumber == 1){
                SchoolboyEntity schoolboyEntity = session.get(SchoolboyEntity.class, fieldId);
                if(schoolboyEntity != null) {
                if(Objects.equals(fieldName,"номер")){
                    schoolboyEntity.setsId(Integer.parseInt(newField));
                }else if(Objects.equals(fieldName,"родитель")){
                    schoolboyEntity.setsPId(Integer.parseInt(newField));
                }else if(Objects.equals(fieldName,"рейтинг")){
                    schoolboyEntity.setsRId(Integer.parseInt(newField));
                }else if(Objects.equals(fieldName,"фамилия")){
                    schoolboyEntity.setsSurname(newField);
                }else if(Objects.equals(fieldName,"имя")){
                    schoolboyEntity.setsFirstName(newField);
                }else if(Objects.equals(fieldName,"отчество")){
                    schoolboyEntity.setsSurname2(newField);
                }else if(Objects.equals(fieldName,"адрес")){
                    schoolboyEntity.setsAddressId(Integer.parseInt(newField));
                }else if(Objects.equals(fieldName,"годрождения")){
                    schoolboyEntity.setsBirthDate(Integer.parseInt(newField));
                }else if(Objects.equals(fieldName,"класс")){
                    schoolboyEntity.setsClassId(Integer.parseInt(newField));
                }else if(Objects.equals(fieldName,"годокончания")){
                    schoolboyEntity.setsAdmYear(Integer.parseInt(newField));
                }
                session.save(schoolboyEntity);
                } else {
                System.out.println("Schoolboy with ID " + fieldId + " not found.");
                }
            }else if (tableNumber == 2){
                ParantsEntity parantsEntity = session.get(ParantsEntity.class, fieldId);
                if(parantsEntity != null) {
                    if(Objects.equals(fieldName,"номер")){
                        parantsEntity.setpId(Integer.parseInt(newField));
                    }else if(Objects.equals(fieldName,"имя")){
                        parantsEntity.setpFirstName(newField);
                    }else if(Objects.equals(fieldName,"фамилия")){
                        parantsEntity.setpSurname(newField);
                    }else if(Objects.equals(fieldName,"отчество")){
                        parantsEntity.setpSurname2(newField);
                    }else if(Objects.equals(fieldName,"адрес")){
                        parantsEntity.setpAddressId(Integer.parseInt(newField));
                    }else if(Objects.equals(fieldName,"статус")){
                        parantsEntity.setpStatus(newField);
                    }
                    session.save(parantsEntity);
                } else {
                    System.out.println("Parent with ID " + fieldId + " not found.");
                }
            }else if (tableNumber == 3){
                AddressEntity addressEntity = session.get(AddressEntity.class, fieldId);
                if(addressEntity != null) {
                    if(Objects.equals(fieldName,"номер")){
                        addressEntity.setAdId(Integer.parseInt(newField));
                    }else if(Objects.equals(fieldName,"адрес")){
                        addressEntity.setAdName(newField);
                    }
                    session.save(addressEntity);
                } else {
                    System.out.println("Address with ID " + fieldId + " not found.");
                }
            }else if (tableNumber == 4){
                ClassEntity classEntity = session.get(ClassEntity.class, fieldId);
                if(classEntity != null) {
                    if(Objects.equals(fieldName,"номер")){
                        classEntity.setClId(Integer.parseInt(newField));
                    }else if(Objects.equals(fieldName,"класс")){
                        classEntity.setClName(newField);
                    }
                    session.save(classEntity);
                } else {
                    System.out.println("Class with ID " + fieldId + " not found.");
                }
            }else if (tableNumber == 5){
                RatingEntity ratingEntity = session.get(RatingEntity.class, fieldId);
                if(ratingEntity != null) {
                    if(Objects.equals(fieldName,"номер")){
                        ratingEntity.setrId(Integer.parseInt(newField));
                    }else if(Objects.equals(fieldName,"год")){
                        ratingEntity.setrYear(Integer.parseInt(newField));
                    }else if(Objects.equals(fieldName,"класс")){
                        ratingEntity.setrClassId(Integer.parseInt(newField));
                    }else if(Objects.equals(fieldName,"предмет")){
                        ratingEntity.setrSubjectId(Integer.parseInt(newField));
                    }else if(Objects.equals(fieldName,"четверть")){
                        ratingEntity.setrPointQuarter(Integer.parseInt(newField));
                    }else if(Objects.equals(fieldName,"полугодие")){
                        ratingEntity.setrPointPartYear(Integer.parseInt(newField));
                    }else if(Objects.equals(fieldName,"оценгод")){
                        ratingEntity.setrPointYear(Integer.parseInt(newField));
                    }else if(Objects.equals(fieldName,"экзамен")){
                        ratingEntity.setrPointExam(Integer.parseInt(newField));
                    }else if(Objects.equals(fieldName,"итоговая")){
                        ratingEntity.setrPointEnd(Integer.parseInt(newField));
                    }
                    session.save(ratingEntity);
                } else {
                    System.out.println("Rating with ID " + fieldId + " not found.");
                }
            }else if (tableNumber == 6){
                SubjectEntity subjectEntity = session.get(SubjectEntity.class, fieldId);
                if(subjectEntity != null) {
                    if(Objects.equals(fieldName,"номер")){
                        subjectEntity.setSuId(Integer.parseInt(newField));
                    }else if(Objects.equals(fieldName,"предмет")){
                        subjectEntity.setSuName(newField);
                    }
                    session.save(subjectEntity);
                } else {
                    System.out.println("Subject with ID " + fieldId + " not found.");
                }
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        }
    }
    //Вывод
    public static String selSchoolboy()  {
        session = getSession();
        StringBuilder result = new StringBuilder(String.format("| %-5s | %-5s | %-5s | %-12s | %-9s | %-10s | %-5s | %-5s | %-5s | %-5s |\n",
                "Номер", "Рейтинг", "Родитель", "Фамилия", "Имя", "Отчество", "Адрес", "Год рождения", "Класс", "Год окончания"));
        try {
            session.beginTransaction();

            Query query = session.createQuery("SELECT MAX(s.sId) FROM SchoolboyEntity s");
            Integer maxId = (Integer) query.uniqueResult();
            for(int i = 1; i <= maxId; i++) {
                SchoolboyEntity contactEntity = session.get(SchoolboyEntity.class, i);
                result.append(String.format("| %-5s | %-5s | %-5s | %-12s | %-9s | %-10s | %-5s | %-5s | %-5s | %-5s |\n",
                        contactEntity.getsId(), contactEntity.getsPId(), contactEntity.getsRId(), contactEntity.getsSurname(), contactEntity.getsFirstName(),
                        contactEntity.getsSurname2(), contactEntity.getsAddressId(), contactEntity.getsBirthDate(),
                        contactEntity.getsClassId(), contactEntity.getsAdmYear()));
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        }
        return result.toString();
    }

    public static String selParant() {
        session = getSession();
        StringBuilder result = new StringBuilder(String.format("| %-5s | %-8s | %-12s | %-12s | %-5s | %-12s |\n",
                "Номер", "Имя", "Фамилия", "Отчество", "Адрес", "Статус"));
        try {
            session.beginTransaction();

            Query query = session.createQuery("SELECT MAX(s.pId) FROM ParantsEntity s");
            Integer maxId = (Integer) query.uniqueResult();
            for(int i = 1; i <= maxId; i++) {
                ParantsEntity contactEntity = session.get(ParantsEntity.class, i);
                result.append(String.format("| %-5s | %-8s | %-12s | %-12s | %-12s | %-5s |\n",
                        contactEntity.getpId(), contactEntity.getpFirstName(),
                        contactEntity.getpSurname(), contactEntity.getpSurname2(),
                        contactEntity.getpAddressId(), contactEntity.getpStatus()));
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        }
        return result.toString();
    }
    public static String selAddress() {
        session = getSession();
        StringBuilder result = new StringBuilder(String.format("| %-5s | %-5s |\n",
                "Номер", "Адрес"));
        try {
            session.beginTransaction();

            Query query = session.createQuery("SELECT MAX(s.adId) FROM AddressEntity s");
            Integer maxId = (Integer) query.uniqueResult();
            for(int i = 1; i <= maxId; i++) {
                AddressEntity contactEntity = session.get(AddressEntity.class, i);
                result.append(String.format("| %-5s | %-12s |\n",
                        contactEntity.getAdId(), contactEntity.getAdName()));
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        }
        return result.toString();
    }
    public static String selClass() {
        session = getSession();
        StringBuilder result = new StringBuilder(String.format("| %-5s | %-5s |\n",
                "Номер", "Класс"));
        try {
            session.beginTransaction();

            Query query = session.createQuery("SELECT MAX(s.clId) FROM ClassEntity s");
            Integer maxId = (Integer) query.uniqueResult();
            for(int i = 1; i <= maxId; i++) {
                ClassEntity contactEntity = session.get(ClassEntity.class, i);
                result.append(String.format("| %-5s | %-5s |\n",
                        contactEntity.getClId(), contactEntity.getClName()));
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        }
        return result.toString();
    }
    public static String selRating() {
        session = getSession();
        StringBuilder result = new StringBuilder(String.format("| %-5s | %-5s | %-5s | %-5s | %-5s | %-5s | %-5s | %-5s | %-5s |\n",
                "Номер", "Год", "Школьник", "Предмет", "Четверть", "Полугодие", "Оцен.Год", "Экзамен", "Итоговая"));
        try {
            session.beginTransaction();

            Query query = session.createQuery("SELECT MAX(s.rId) FROM RatingEntity s");
            Integer maxId = (Integer) query.uniqueResult();
            for(int i = 1; i <= maxId; i++) {
                RatingEntity contactEntity = session.get(RatingEntity.class, i);
                result.append(String.format("| %-5s | %-5s | %-5s | %-5s | %-5s | %-5s | %-5s | %-5s | %-5s |\n",
                        contactEntity.getrId(), contactEntity.getrYear(), contactEntity.getrClassId(), contactEntity.getrSubjectId(),
                        contactEntity.getrPointQuarter(), contactEntity.getrPointPartYear(), contactEntity.getrPointYear(),
                        contactEntity.getrPointExam(), contactEntity.getrPointEnd()));
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        }
        return result.toString();
    }
    public static String selSubject() {
        session = getSession();
        StringBuilder result = new StringBuilder(String.format("| %-12s | %-12s |\n",
                "Номер", "Предмет"));
        try {
            session.beginTransaction();

            Query query = session.createQuery("SELECT MAX(s.suId) FROM SubjectEntity s");
            Integer maxId = (Integer) query.uniqueResult();
            for(int i = 1; i <= maxId; i++) {
                SubjectEntity contactEntity = session.get(SubjectEntity.class, i);
                result.append(String.format("| %-5s | %-12s |\n",
                        contactEntity.getSuId(), contactEntity.getSuName()));
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        }
        return result.toString();
    }
    public static String search(String needed){
        StringBuilder result = new StringBuilder();
        result.append(searchAddress(needed));
        result.append(searchClass(needed));
        result.append(searchParants(needed));
        result.append(searchRating(needed));
        result.append(searchSchoolboy(needed));
        result.append(searchSubject(needed));
        return result.toString();
    }
    public static String searchAddress(String needed){
        session = getSession();
        StringBuilder result = new StringBuilder();
        try {
            session.beginTransaction();

            Query query = session.createQuery("SELECT MAX(s.adId) FROM AddressEntity s");
            Integer maxAId = (Integer) query.uniqueResult();
            for (int i = 1; i <= maxAId; i++) {
                AddressEntity addressEntity = session.get(AddressEntity.class, i);
                if (needed.equals(addressEntity.getAdId())) {
                    appendAddress(result, addressEntity);
                } else if (needed.equals(addressEntity.getAdName())) {
                    appendAddress(result, addressEntity);
                }
            }
            session.getTransaction().commit();
        }catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        }
        return result.toString();
    }
    public static String searchClass(String needed){
        session = getSession();
        StringBuilder result = new StringBuilder();
        try {
            session.beginTransaction();
            Query query = session.createQuery("SELECT MAX(s.clId) FROM ClassEntity s");
            Integer maxClId = (Integer) query.uniqueResult();

            for (int i = 1; i <= maxClId; i++) {
                ClassEntity classEntity = session.get(ClassEntity.class, i);
                if (needed.equals(classEntity.getClId())) {
                    appendClass(result, classEntity);
                } else if (needed.equals(classEntity.getClName())) {
                    appendClass(result, classEntity);
                }
            }
            session.getTransaction().commit();
        }catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        }
        return result.toString();
    }

    public static int searchClassName(String needed){
        session = getSession();
        StringBuilder result = new StringBuilder();
        try {
            session.beginTransaction();
            Query query = session.createQuery("SELECT MAX(s.clId) FROM ClassEntity s");
            Integer maxClId = (Integer) query.uniqueResult();

            for (int i = 1; i <= maxClId; i++) {
                ClassEntity classEntity = session.get(ClassEntity.class, i);
                if (Objects.equals(needed, classEntity.getClName())) {
                    result.append(classEntity.getClId());
                }else{
                    System.out.println("Мимо");
                }
            }
            session.getTransaction().commit();
        }catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        }
        System.out.println(Integer.parseInt(result.toString()));
        return Integer.parseInt(result.toString());
    }
    public static String searchParants(String needed){
        session = getSession();
        StringBuilder result = new StringBuilder();
        try {
            session.beginTransaction();
            Query query = session.createQuery("SELECT MAX(s.pId) FROM ParantsEntity s");
            Integer maxPId = (Integer) query.uniqueResult();
            for (int i = 1; i <= maxPId; i++) {
                ParantsEntity parantsEntity = session.get(ParantsEntity.class, i);
                if (needed.equals(parantsEntity.getpId())) {
                    appendParants(result, parantsEntity);
                } else if (needed.equals(parantsEntity.getpSurname())) {
                    appendParants(result, parantsEntity);
                } else if (needed.equals(parantsEntity.getpFirstName())) {
                    appendParants(result, parantsEntity);
                } else if (needed.equals(parantsEntity.getpSurname2())) {
                    appendParants(result, parantsEntity);
                } else if (needed.equals(parantsEntity.getpAddressId())) {
                    appendParants(result, parantsEntity);
                } else if (needed.equals(parantsEntity.getpStatus())) {
                    appendParants(result, parantsEntity);
                }
            }
            session.getTransaction().commit();
        }catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        }
        return result.toString();
    }
    public static String searchRating(String needed){
        session = getSession();
        StringBuilder result = new StringBuilder();
        try {
            session.beginTransaction();
            Query query = session.createQuery("SELECT MAX(s.rId) FROM RatingEntity s");
            Integer maxRId = (Integer) query.uniqueResult();
            for (int i = 1; i <= maxRId; i++) {
                RatingEntity ratingEntity = session.get(RatingEntity.class, i);
                if (needed.equals(ratingEntity.getrId())) {
                    appendRating(result, ratingEntity);
                } else if (needed.equals(ratingEntity.getrYear())) {
                    appendRating(result, ratingEntity);
                } else if (needed.equals(ratingEntity.getrClassId())) {
                    appendRating(result, ratingEntity);
                } else if (needed.equals(ratingEntity.getrSubjectId())) {
                    appendRating(result, ratingEntity);
                } else if (needed.equals(ratingEntity.getrPointQuarter())) {
                    appendRating(result, ratingEntity);
                } else if (needed.equals(ratingEntity.getrPointPartYear())) {
                    appendRating(result, ratingEntity);
                } else if (needed.equals(ratingEntity.getrPointYear())) {
                    appendRating(result, ratingEntity);
                } else if (needed.equals(ratingEntity.getrPointExam())) {
                    appendRating(result, ratingEntity);
                } else if (needed.equals(ratingEntity.getrPointEnd())) {
                    appendRating(result, ratingEntity);
                }
            }
            session.getTransaction().commit();
        }catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        }
        return result.toString();
    }
    public static String searchSchoolboy(String needed){
        session = getSession();
        StringBuilder result = new StringBuilder();
        try {
            session.beginTransaction();
            Query query = session.createQuery("SELECT MAX(s.sId) FROM SchoolboyEntity s");
            Integer maxSId = (Integer) query.uniqueResult();
            for (int i = 1; i <= maxSId; i++) {
                SchoolboyEntity schoolboyEntity = session.get(SchoolboyEntity.class, i);
                if (needed.equals(schoolboyEntity.getsId())) {
                    appendSchoolboy(result, schoolboyEntity);
                } else if (needed.equals(schoolboyEntity.getsPId())) {
                    appendSchoolboy(result, schoolboyEntity);
                } else if (needed.equals(schoolboyEntity.getsRId())) {
                    appendSchoolboy(result, schoolboyEntity);
                }else if (needed.equals(schoolboyEntity.getsSurname())) {
                    appendSchoolboy(result, schoolboyEntity);
                }else if (needed.equals(schoolboyEntity.getsFirstName())) {
                    appendSchoolboy(result, schoolboyEntity);
                }else if (needed.equals(schoolboyEntity.getsSurname2())) {
                    appendSchoolboy(result, schoolboyEntity);
                }else if (needed.equals(schoolboyEntity.getsAddressId())) {
                    appendSchoolboy(result, schoolboyEntity);
                }else if (needed.equals(schoolboyEntity.getsBirthDate())) {
                    appendSchoolboy(result, schoolboyEntity);
                }else if (needed.equals(schoolboyEntity.getsClassId())) {
                    appendSchoolboy(result, schoolboyEntity);
                }else if (needed.equals(schoolboyEntity.getsAdmYear())) {
                    appendSchoolboy(result, schoolboyEntity);
                }
            }
            session.getTransaction().commit();
        }catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        }
        return result.toString();
    }
    public static String searchSubject(String needed){
        session = getSession();
        StringBuilder result = new StringBuilder();
        try {
            session.beginTransaction();
            Query query = session.createQuery("SELECT MAX(s.suId) FROM SubjectEntity s");
            Integer maxSuId = (Integer) query.uniqueResult();
            for (int i = 1; i <= maxSuId; i++) {
                SubjectEntity subjectEntity = session.get(SubjectEntity.class, i);
                if (needed.equals(subjectEntity.getSuId())) {
                    appendSubject(result, subjectEntity);
                } else if (needed.equals(subjectEntity.getSuName())) {
                    appendSubject(result, subjectEntity);
                }
            }
            session.getTransaction().commit();
        }catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        }
        return result.toString();
    }

    public static StringBuilder appendAddress(StringBuilder result, AddressEntity addressEntity){
        result.append("Откуда | Номер | Адрес\n");
        result.append("Адрес | " + addressEntity.getAdId());
        result.append(" " + addressEntity.getAdName());
        result.append("\n\n");
        return result;
    }
    public static StringBuilder appendClass(StringBuilder result, ClassEntity classEntity){
        result.append("Откуда | Номер | Класс\n");
        result.append("Класс | "+ classEntity.getClId());
        result.append(" | " + classEntity.getClName());
        result.append("\n\n");
        return result;
    }
    public static StringBuilder appendParants(StringBuilder result, ParantsEntity parantsEntity){
        result.append("Откуда | Номер | Имя | Фамилия | Отчество | Адрес | Статус\n");
        result.append("Родитель | " + parantsEntity.getpId());
        result.append(" | " + parantsEntity.getpSurname());
        result.append(" | " + parantsEntity.getpFirstName());
        result.append(" | " + parantsEntity.getpSurname2());
        result.append(" | " + parantsEntity.getpAddressId());
        result.append(" | " + parantsEntity.getpStatus());
        result.append("\n\n");
        return result;
    }
    public static StringBuilder appendRating(StringBuilder result, RatingEntity ratingEntity){
        result.append("Откуда | Номер | Год | Класс | Предмет | Четверть | Полугодие | Оцен.год | Экзамен | Итоговая\n");
        result.append("Рейтинг | " + ratingEntity.getrId());
        result.append(" | " + ratingEntity.getrYear());
        result.append(" | " + ratingEntity.getrClassId());
        result.append(" | " + ratingEntity.getrSubjectId());
        result.append(" | " + ratingEntity.getrPointQuarter());
        result.append(" | " + ratingEntity.getrPointPartYear());
        result.append(" | " + ratingEntity.getrPointYear());
        result.append(" | " + ratingEntity.getrPointExam());
        result.append(" | " + ratingEntity.getrPointEnd());
        result.append("\n\n");
        return result;
    }
    public static StringBuilder appendSchoolboy(StringBuilder result, SchoolboyEntity schoolboyEntity){
        result.append("Откуда | Номер | Рейтинг | Фамилия | Имя | Отчество | Адрес | Год рождения | Класс | Год окончания\n");
        result.append("Школьник | " + schoolboyEntity.getsId());
        result.append(" | " + schoolboyEntity.getsPId());
        result.append(" | " + schoolboyEntity.getsRId());
        result.append(" | " + schoolboyEntity.getsSurname());
        result.append(" | " + schoolboyEntity.getsFirstName());
        result.append(" | " + schoolboyEntity.getsSurname2());
        result.append(" | " + schoolboyEntity.getsAddressId());
        result.append(" | " + schoolboyEntity.getsBirthDate());
        result.append(" | " + schoolboyEntity.getsClassId());
        result.append(" | " + schoolboyEntity.getsAdmYear());
        result.append("\n\n");
        return result;
    }
    public static StringBuilder appendSubject(StringBuilder result, SubjectEntity subjectEntity){
        result.append("Откуда | Номер | Предмет\n");
        result.append("Предмет | " + subjectEntity.getSuId());
        result.append(" | " + subjectEntity.getSuName());
        result.append("\n\n");
        return result;
    }


    public static void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}