const Reducer = (cart=[], action) => {
    switch (action.type) {
        case 'ADD':
           let addToCart=  cart.filter((element) => (element.productId === action.payload.productId || element.product.productId !== action.payload.product.productId))
            if(addToCart<1){
                return[...cart,action.payload]
            }else{
                return cart;
            }
        break;

        case 'REMOVE':
            return cart.filter((element)=>element.productId!==action.payload.productId)
            break;
        
            case 'EMPTY':
            return cart=[]
            // break;
        default:
            return cart;
    }
}

export default Reducer;
