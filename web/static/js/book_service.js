'use strict';

angular.module('myApp').factory('BookService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = 'http://localhost:8080/book/';
    var factory = {
        fetchAllBooks: fetchAllBooks,
        createBook: createBook,
        updateBook:updateBook,
        deleteBook:deleteBook,
        read:read,
        fetchBooks:fetchBooks
    };
    return factory;

    function fetchAllBooks() {
        var deferred = $q.defer();
        $http.get('http://localhost:8080/allBooks/')
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while fetching Books');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function createBook(book) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI, book)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while creating Book');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }


    function updateBook(id, book) {
        var deferred = $q.defer();
        $http.put(REST_SERVICE_URI+id, book)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while updating Book');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }


    function deleteBook(id) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI+id)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while deleting Book');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function read(id, book) {
        var deferred = $q.defer();
        $http.put('http://localhost:8080/read/'+id, book)
            .then(
                function (response) {
                    deferred.resolve(response.data)
                },
                function (errResponse) {
                    console.error('Error while reading Book');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function fetchBooks(page) {
        var deferred = $q.defer();
        $http.get('http://localhost:8080/books/p='+page)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function (errResponse) {
                    console.error("Error while fetching page");
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }
}]);