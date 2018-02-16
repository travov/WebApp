'use strict';

angular.module('myApp').controller('BookController', ['$scope', 'BookService', function($scope, BookService) {
    var self = this;
    self.book={id:null,title:'',description:'',author:'', isbn:'', year:'', readAlready:false};
    self.books=[];

    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;
    self.read = read;
    self.fetchBooks = fetchBooks;
    $scope.searchText ='';

    self.filtered = [];
    self.currentPage = 0;
    self.numPerPage = 10;
    self.maxSize = 5;

    fetchAllBooks();
    fetchBooks(self.currentPage);


    $scope.$watch('currentPage', fetchBooks(self.currentPage));

    self.getNumber = function(num) {
        return new Array(num);
    };

    function fetchAllBooks(){
        BookService.fetchAllBooks()
            .then(
                function(d) {
                    self.books = d;
                },
                function(errResponse){
                    console.error('Error while fetching Books');
                }
            );
    }
    function fetchBooks(page) {
        BookService.fetchBooks(page)
            .then(
                function (d) {
                    self.filtered = d;
                },
                function (errResponse) {
                    console.error("Error while fetching page");
                }
            );
    }

    function createBook(book){
        BookService.createBook(book)
            .then(
                function () {
                    fetchAllBooks();
                    fetchBooks(self.currentPage);
                },
                function(errResponse){
                    console.error('Error while creating Book');
                }
            );
    }

    function updateBook(id, book){
        BookService.updateBook(id, book)
            .then(
                function () {
                    fetchAllBooks();
                    fetchBooks(self.currentPage);
                },
                function(errResponse){
                    fetchAllBooks();
                    console.error('Error while updating Book');
                }
            );
    }

    function deleteBook(id){
        BookService.deleteBook(id)
            .then(
                function () {
                    fetchAllBooks();
                    fetchBooks(self.currentPage);
                },
                function(errResponse){
                    console.error('Error while deleting Book');
                }
            );
    }

    function read(id, book) {
        BookService.read(id,book)
            .then(
                function () {
                    fetchAllBooks();
                    fetchBooks(self.currentPage);
                },
                function (errResponse) {
                    console.error('Error while reading Book');
                }
            );
        reset();
    }

    function submit() {
        if(self.book.id===null){
            console.log('Saving New Book', self.book);
            createBook(self.book);
        }else{
            updateBook(self.book.id, self.book);
            console.log('Book updated with id ', self.book.id);
        }
        reset();
    }

    function edit(id){
        console.log('id to be edited', id);
        for(var i = 0; i < self.books.length; i++){
            if(self.books[i].id === id) {
                self.book = angular.copy(self.books[i]);
                break;
            }
        }
    }

    function remove(id){
        console.log('id to be deleted', id);
        if(self.book.id === id) {//clean form if the book to be deleted is shown there.
            reset();
        }
        deleteBook(id);
    }


    function reset(){
        self.book={id:null,title:'',description:'',author:'', isbn:'', year:''};
        $scope.myForm.$setPristine(); //reset Form
    }

    self.numPages = function () {
        return Math.ceil(self.books.length / self.numPerPage);
    };

}]);

angular.module('myApp').filter('searchFilter', function () {
    return function(arr, searchString) {

        if(!searchString) {
            return arr;
        }

        searchString = searchString.toLowerCase();

        var result = [];

        angular.forEach(arr, function(el){
            if(el.title.toLowerCase().indexOf(searchString) !== -1
            || el.description.toLowerCase().indexOf(searchString) !== -1
            || el.author.toLowerCase().indexOf(searchString) !== -1
            || el.isbn.toLowerCase().indexOf(searchString) !== -1
                || el.year.toString().indexOf(searchString) !== -1) {
                result.push(el);
            }
        });

        return result;
    };
});