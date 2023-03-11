import axios from 'axios';
import React from 'react';
import { useHistory, useParams } from 'react-router-dom';

import Button from './Button';

const Editar = () => {
    const params = useParams();
    const history = useHistory();

    const handleBackButtonClick = () => {
        history.goBack();
    }

    
    const atualizarProduto = () => {
        const nome = document.querySelector("#nome")
        const preco = document.querySelector("#preco")
        const quantidade = document.querySelector("#quantidade")

        const idProduto = params.idProd

        const url = `http://localhost:8080/produtos/${idProduto}`
        const dados = {
            "nome": nome.value,
            "preco": preco.value,
            "quantidadeEstoque": quantidade.value
        }
        const config = {
            headers: {}
        }
        
        axios.put(url, dados, config).then((response) => {
            console.log(response.data)
            history.goBack()
        })

    }

    return (
        <>
            <h2>Formulário de Edição!</h2>
            <form method="POST">
                <div>
                    <label for="nome">Nome</label>
                    <input type="text" name="nome" id="nome"></input>
                </div>
                <div>
                    <label for="preco">Preço</label>
                    <input type="number" name="preco" id="preco"></input>
                </div>
                <div>
                    <label for="quantidade">Quantidade</label>
                    <input type="number" name="quantidade" id="quantidade"></input>
                </div>
                <button type="submit" onClick={atualizarProduto}>Atualizar</button>
            </form>
            <Button onClick={handleBackButtonClick}>Voltar</Button>
        </>
    )
}
export default Editar;