package com.phycaresolutions.mymap.roomdatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface QueryServiceCall {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertData(PersonDetails details);

 /* @Query("SELECT * FROM person")
  LiveData<List<PersonDetails> getDetails();*/
  @Query("SELECT * FROM person")
  LiveData<List<PersonDetails>> getData();

  @Update
  void updateData(PersonDetails details);

 //delete
  @Query("DELETE FROM person WHERE id = :id")
  int  deleteUser(String id);

  //  payment table  -- ///
 /* @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertPayment(payment: Payment)

  @Query("SELECT * FROM rentPayment WHERE mobileNumber = :mobileNumber ORDER BY month")
  fun getUserAllPayments(mobileNumber: String):List<Payment>

  @Update
  fun paymentUpdate(payment: Payment)

  @Query("SELECT EXISTS(SELECT * FROM rentPayment WHERE month = :month AND year = :year AND mobileNumber = :mobileNumber )")
  fun isRecordExistsUserId(month: String,year:String,mobileNumber:String): Boolean

  //delete
  @Query("DELETE FROM rentPayment WHERE mobileNumber = :mobileNumber")
  fun deletepayUser(mobileNumber: String)

  @Query("DELETE FROM rentPayment WHERE month = :month")
  fun deletepayMonth(month: String)

  // powerWater bill table Insert
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertPowerWaterPayment(powerWaterPayment: PowerWaterPayment)

  @Query("SELECT EXISTS(SELECT * FROM powerWaterBill WHERE month = :month AND year = :year AND mobileNumber = :mobileNumber )")
  fun isRecordExistsPowerBill(month: String,year:String,mobileNumber:String): Boolean

  @Query("SELECT * FROM powerWaterBill WHERE month = :month AND year = :year AND mobileNumber = :mobileNumber ")
  fun getUserAllPowerPayments(month: String,year:String,mobileNumber:String):List<PowerWaterPayment>

  @Update
  fun powerBillUpdate(payment: PowerWaterPayment)

  @Query("DELETE FROM powerWaterBill WHERE mobileNumber = :mobileNumber")
  fun deletePowerBillUser(mobileNumber: String)
  @Query("DELETE FROM powerWaterBill WHERE month = :month")
  fun deletePowerMonth(month: String)*/
}
