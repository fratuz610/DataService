/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.holiday69.dataservice;

import it.holiday69.dataservice.query.Query;
import java.util.List;

/**
 *
 * @author fratuz610
 */
public abstract class DataService {
  
  private Throwable _lastError;
  
  /***
   * Inserts the given object into the database
   * </p>
   * The data is persisted immediately
   * 
   * @param <T> The type of the object to insert
   * @param object The object to insert
   */
  public abstract <T> void put(T object);
  
  /**
   * Inserts all the given object into the database
   * </p>
   * The data is persisted immediately
   * 
   * @param <T>
   * @param entities A collection of entities to save into the database
   */
  public abstract <T> void putAll(Iterable<T> entities);
  
  /**
   * Extract an entity from the database based on its key
   * @param <T>
   * @param <V>
   * @param key The key used for extraction
   * @param classOfT The class of the entity to retrieve
   * @return the entity extracted
   */
  public abstract <T, V> T get(V key, Class<T> classOfT);
  
  /**
   * Extract one entity from the database with a matching field value
   * @param <T>
   * @param fieldName The name of the field used to query
   * @param fieldValue the value of the field used to query
   * @param classOfT The class of the entity to retrieve
   * @return One entity from the database matching the field value specified
   */
  public abstract <T> T get(String fieldName, Object fieldValue, Class<T> classOfT);
  
  /**
   * Extract one entity from the database matching the specified {@link Query}
   * @param <T>
   * @param query The {@link Query} object
   * @param classOfT The class of the entity to retrieve
   * @return the first entity matching the query on the specified entity
   */
  public abstract <T> T get(Query query, Class<T> classOfT);
  
  /**
   * Extract one entity from the database of the specified class
   * @param <T>
   * @param classOfT The class of the entity to retrieve
   * @return An entity of the specified class
   */
  public abstract <T> T get(Class<T> classOfT);
  
  /**
   * Extract a list of entities from the database matching the specified field value
   * @param <T>
   * @param fieldName The name of the field used to query
   * @param fieldValue the value of the field used to query
   * @param classOfT The class of the entities to retrieve
   * @return A list of entities matching the the field value specified
   */
  public abstract <T> List<T> getList(String fieldName, Object fieldValue, Class<T> classOfT);
  
  /**
   * Extract all the entities from the database of a specific class
   * @param <T>
   * @param classOfT The class of the entities to retrieve
   * @return all the entities of a specific class
   */
  public abstract <T> List<T> getList(Class<T> classOfT);
  
  /**
   * Extract a list of entities from the database matching the specified {@link Query}
   * @param <T>
   * @param query The {@link Query} object
   * @param classOfT The class of the entities to retrieve
   * @return all the entities of a specific class matching the specified {@link Query}
   */
  public abstract <T> List<T> getList(Query query, Class<T> classOfT);
  
  /**
   * Deletes an object from the database
   * </p>
   * The object is extracted using the id field value
   * If the object isn't present in the database, the call fails silently
   * @param <T>
   * @param object The object to delete
   */
  public abstract <T> void delete(T object);
	
  /**
   * Deletes all objects in the collection provided
   * </p>
   * This can be significantly faster than calling {@link DataService.delete() } on every single item
   * @param <T>
   * @param entities All the objects to delete
   */
	public abstract <T> void deleteAll(Iterable<T> entities);
  
  /**
   * Deletes all objects of the specified class matching the provided {@link Query}
   * @param <T>
   * @param query The {@link Query} used to retrieve the dataset
   * @param classOfT The class of the objects to delete
   */
  public abstract <T> void deleteAll(Query query, Class<T> classOfT);
  
  /**
   * Deletes all objects of a specified class
   * @param <T>
   * @param classOfT The class of the objects to delete
   */
  public abstract <T> void deleteAll(Class<T> classOfT);
  
  /**
   * Returns the number of objects of a specified class
   * </p>
   * The return value is calculated using a key-only query, considerably faster than extracting the full dataset
   * @param <T>
   * @param classOfT The class of the object to query for
   * @return The number of objects of the specified class
   */
  public abstract <T> long getResultSetSize(Class<T> classOfT);
  
  /**
   * Returns the number of objects of a specified class matching the argument field filter
   * </p>
   * The return value is calculated using a key-only query, considerably faster than extracting the full dataset
   * @param <T>
   * @param fieldName The name of the field to apply the filter on
   * @param fieldValue The value of the field used to filter
   * @param classOfT The class of objects to query for
   * @return The number of objects of the specified class matching the specified field filter
   */
  public abstract <T> long getResultSetSize(String fieldName, Object fieldValue, Class<T> classOfT);
  
  /**
   * Returns the number of objects of a specified class matching the specified {@link Query}
   * @param <T>
   * @param query The {@link Query} to use as a filter
   * @param classOfT The class of objects to query for
   * @return The number of objects of the specified class matching the specified query filter
   */
  public abstract <T> long getResultSetSize(Query query, Class<T> classOfT);
  
  public Throwable getLastError() { return _lastError; }
    
}
