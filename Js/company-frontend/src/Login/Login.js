import logo from './logo.svg';
import React, { Component } from 'react';
import './App.css';
import {getAllStudents} from './Client'
import Container from './Container';
import {Table,Avatar,Spin} from 'antd';
import { LoadingOutlined } from '@ant-design/icons';

const getIndicatorIcon = () =>  <LoadingOutlined style={{ fontSize: 24 }} spin />;

class App extends Component{

    state = {
        students: [],
        isFetching: false
      }
    
      componentDidMount(){
        this.fetchStudents();
      }
    
      fetchStudents = () => {
    
        this.setState({isFetching : true});
    
        getAllStudents()
          .then(res => res.json()
          .then(students => {
            console.log(students); 
            this.setState({ students , isFetching : false }); 
          })
        );

}