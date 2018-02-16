<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jstl/core_rt" %>
<%--
  Created by IntelliJ IDEA.
  User: Ilya
  Date: 01.02.2018
  Time: 21:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Books</title>
    <style>
        .title.ng-valid {
            background-color: lightgreen;
        }
        .title.ng-dirty.ng-invalid-required {
            background-color: #F08080;
        }
        .title.ng-dirty.ng-invalid-minlength {
            background-color: #e7f28e;
        }
        .description.ng-valid {
            background-color: lightgreen;
        }
        .description.ng-dirty.ng-invalid-required {
            background-color: #F08080;
        }
        .author.ng-valid{
            background-color: lightgreen;
        }
        .author.ng-dirty.ng-invalid-required{
            background-color: #F08080;
        }
        .author.ng-dirty.ng-invalid-minlength {
            background-color: #e7f28e;
        }

        .isbn.ng-valid{
            background-color: lightgreen;
        }
        .isbn.ng-dirty.ng-invalid-required{
            background-color: #F08080;
        }
        .year.ng-valid{
            background-color: lightgreen;
        }
        .year.ng-dirty.ng-invalid-required{
            background-color: #F08080;
        }
        .paginator {
            position: relative;
            margin: 0 auto;
        }
        .paginator ul {
            position: absolute;
            left: 50%;
            transform: translateX(-50%);
        }
        .paginator li {
            float:left;
        }
    </style>
    <link href="<c:url value='/static/js/app.css' />" rel="stylesheet"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>

<body ng-app="myApp" class="ng-cloak">
<div align="center" ng-controller="BookController as ctrl">
<nav class="navbar" style="width: 75%; background-color: #6eafe8; border-radius: 4px">
    <a class="navbar-brand"><span style="color: #ffffff; margin-left: 30px"> Book Registration</span></a>
    <form class="form-inline"  style="margin-top: 4px">

    </form>
</nav>
</div>
<div class="generic-container" ng-controller="BookController as ctrl">

    <div class="panel panel-default">
        <input class="form-control mr-sm-2" ng-model="ctrl.searchText" name="searchInput" type="text" placeholder="Search">
        <div class="formcontainer">
            <div align="center">
            <form  name="myForm" class="form-horizontal">
                <input type="hidden" ng-model="ctrl.book.id" />
                <div class="row" style="margin-bottom: 10px">
                    <label class="col-md-2 control-lable"  style="color: #ffffff;font-size: large">Title:</label>
                    <div class="col-md-7">
                        <input type="text" ng-model="ctrl.book.title" name="title" class="title form-control input-sm" placeholder="Enter title of book" required ng-minlength="3"/>
                           <div class="has-error" ng-show="myForm.title.$dirty">
                                <span ng-show="myForm.title.$error.required">This is a required field</span>
                                <span ng-show="myForm.title.$error.minlength">Minimum length required is 3</span>

                            </div>
                    </div>
                </div>

                <div class="row" style="margin-bottom: 10px">

                        <label class="col-md-2 control-lable"  style="color: #ffffff;font-size: large">Description:</label>
                        <div class="col-md-7">
                            <input type="text" ng-model="ctrl.book.description" name="description" class="description form-control input-sm" placeholder="Enter description of your book" required ng-minlength="3"/>
                            <div class="has-error" ng-show="myForm.description.$dirty">
                                <span ng-show="myForm.description.$error.required">This is a required field</span>
                            </div>
                        </div>
                </div>

                <div class="row" style="margin-bottom: 10px">
                        <label class="col-md-2 control-lable"  style="color: #ffffff;font-size: large">Author:</label>
                        <div class="col-md-7">
                            <input type="text" ng-model="ctrl.book.author" name="author" class="author form-control input-sm" placeholder="Enter the author" required ng-minlength="3"/>
                            <div class="has-error" ng-show="myForm.author.$dirty">
                                <span ng-show="myForm.author.$error.required">This is a required field</span>
                                <span ng-show="myForm.author.$error.minlength">Minimum length required is 3</span>
                            </div>
                        </div>
                </div>

                <div class="row" style="margin-bottom: 10px">
                        <label class="col-md-2 control-lable"  style="color: #ffffff;font-size: large">ISBN:</label>
                        <div class="col-md-7">
                            <input type="text" ng-model="ctrl.book.isbn" name="isbn" class="isbn form-control input-sm" placeholder="Enter the ISBN" required ng-minlength="1"/>
                            <div class="has-error" ng-show="myForm.isbn.$dirty">
                                <span ng-show="myForm.isbn.$error.required">This is a required field</span>
                                <span ng-show="myForm.isbn.$error.minlength">Minimum length required is 1</span>
                            </div>
                        </div>
                </div>

                <div class="row">
                        <label class="col-md-2 control-lable"  style="color: #ffffff;font-size: large">Year:</label>
                        <div class="col-md-7">
                            <input type="number" ng-model="ctrl.book.year" name="year" class="year form-control input-sm" placeholder="Enter the year" required ng-minlength="1"/>
                            <div class="has-error" ng-show="myForm.year.$dirty">
                                <span ng-show="myForm.year.$error.required">This is a required field</span>
                                <span ng-show="myForm.year.$error.minlength">Minimum length required is 1</span>
                            </div>
                        </div>
                </div>

                <div align="right">
                    <div class="form-actions floatRight" >
                        <input type="button" ng-click="ctrl.submit()"  value="{{!ctrl.book.id ? 'Add' : 'Update'}}" class="btn btn-outline-light" ng-disabled="myForm.$invalid">
                        <button type="button" ng-click="ctrl.reset()" class="btn btn-outline-danger" ng-disabled="myForm.$pristine">Reset Form</button>
                    </div>
                </div>
                <div align="center">
                    <input type="button" ng-show="ctrl.book.id && !ctrl.book.readAlready" ng-click="ctrl.read(ctrl.book.id, ctrl.book)" value="Already Read" class="btn btn-outline-light">
                </div>
            </form>
            </div>
        </div>
    </div>

    <div class="panel panel-default">
        <div class="panel-heading"><span class="lead"></span> </div>
        <div class="tablecontainer">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th></th>
                  <th>ID</th>
                  <th>Title</th>
                  <th>Description</th>
                  <th>Author</th>
                  <th>ISBN</th>
                  <th>Year</th>
                  <th width="20%"></th>
                </tr>
                </thead>

                <tbody>
                <tr ng-repeat="b in ctrl.filtered | searchFilter:ctrl.searchText">
                    <td><span ng-show="b.readAlready == true"><input type="button" class="btn btn-success btn-sm" value="read"> </span> </td>
                    <td>{{b.id}} </td>
                    <td>{{b.title}} </td>
                    <td>{{b.description}} </td>
                    <td>{{b.author}} </td>
                    <td>{{b.isbn}} </td>
                    <td>{{b.year}} </td>
                    <td>
                        <button type="button" ng-click="ctrl.edit(b.id)" class="btn btn-outline-success">Edit</button>
                        <button type="button" ng-click="ctrl.remove(b.id)" class="btn btn-outline-danger">Remove</button>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
    <div class="paginator" style="margin-bottom: 5%">
        <ul class="pagination">
            <input type="button" class="btn btn-outline-primary" ng-disabled="ctrl.currentPage == 0" ng-click="ctrl.fetchBooks(ctrl.currentPage=ctrl.currentPage - 1)" value="Previous">
            <li ng-repeat="i in ctrl.getNumber(ctrl.numPages()) track by $index"><input type="button" class="btn btn-light" value="{{$index + 1}}" ng-click="ctrl.fetchBooks($index); ctrl.currentPage=$index">  </li>
            <input type="button" class="btn btn-outline-primary" ng-disabled="ctrl.currentPage  == ctrl.numPages() - 1" ng-click="ctrl.fetchBooks(ctrl.currentPage=ctrl.currentPage + 1)" value="Next">
        </ul>
    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.7/angular.js"></script>
<script src="${pageContext.request.contextPath}/static/js/app.js"></script>
<script src="${pageContext.request.contextPath}/static/js/book_service.js"></script>
<script src="${pageContext.request.contextPath}/static/js/book_controller.js"></script>
</body>
</html>
