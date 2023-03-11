import React, { useState } from 'react';
import axios from 'axios';
import { BrowserRouter as Router, Route, useHistory } from 'react-router-dom';

import Header from './components/Header';
import Produtos from './components/Produtos';
import Editar from './components/Editar';
import Cadastrar from './components/Cadastrar';
import Button from './components/Button';

import './App.css';

const ButtonCadastro = () => {
  const history = useHistory()

  const handleCadastrarProduto = () => {
    history.push('/cadastrar')
  }

  return (
    <Button onClick={handleCadastrarProduto}>Cadastrar</Button>
  )
}


const App = () => {
  const [produtos, setProdutos] = useState([])
  
  const verProdutos = (event) => {
    const url = 'http://localhost:8080/produtos';
    const config = {
      headers: {},
    };
    axios.get(url, config).then((response) => {
      setProdutos(response.data)
      
    });
  }
  
  const handleProdutoDeletion = (produtoId) => {
    const newProdutos = produtos.filter(produto => produto.id != produtoId)
    
    setProdutos(newProdutos);
  }
  
  return (
    <Router>
      <div className="App">
        <Header />
        <Route
          path="/"
          exact
          render={() => (
            <>
              <ButtonCadastro />
              <Button onClick={verProdutos}>Ver produtos</Button>
              <Produtos produtos={produtos} handleProdutoDeletion={handleProdutoDeletion} />
            </>
          )}
        >
        </Route>
        <Route
          path="/:idProd/:nomeProd/:precoProd/:estoqueProd"
          exact
          component={Editar}
        >
        </Route>
        <Route
          path="/cadastrar"
          exact
          component={Cadastrar}
        >
        </Route>
      </div>
    </Router>
  );
}

export default App;