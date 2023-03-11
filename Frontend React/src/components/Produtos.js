import React from 'react';
import Produto from './Produto';

const Produtos = ( { produtos , handleProdutoDeletion } ) => {
    return (
      <>
        {produtos.map(produto =>
            <Produto produto={produto} handleProdutoDeletion={handleProdutoDeletion} />
        )}
      </>
    );
}



export default Produtos;
