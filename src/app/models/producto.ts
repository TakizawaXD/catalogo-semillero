export interface Producto {
    id: number;
    title: string;
    price: number;
    description: string;
    image: string;
    stock: number;
    category: {
        id: number;
        name: string;
    };
}

export const productos : Producto[] = [
    {
        id: 1,
        title: 'Producto 1',
        price: 10000,
        description: 'Descripción del producto 1',
        image: 'https://via.placeholder.com/150',
        stock: 5,
        category: {
            id: 1,
            name: 'Categoría 1',
        },
    },
    {
        id: 2,
        title: 'Producto 2',
        price: 20000,
        description: 'Descripción del producto 2',
        image: 'https://via.placeholder.com/150',
        stock: 0,
        category: {
            id: 2,
            name: 'Categoría 2',
        },
    },
    {
        id: 3,
        title: 'Producto 3',
        price: 30000,
        description: 'Descripción del producto 3',
        image: 'https://via.placeholder.com/150',
        stock: 10,
        category: {
            id: 3,
            name: 'Categoría 3',
        },
    },
    {   
        id: 4,
        title: 'Producto 4',
        price: 40000,
        description: 'Descripción del producto 4',
        image: 'https://via.placeholder.com/150',
        stock: 2,
        category: {
            id: 4,
            name: 'Categoría 4',
        },  
    },
    {
        id: 5,
        title: 'Producto 5',
        price: 50000,
        description: 'Descripción del producto 5',
        image: 'https://via.placeholder.com/150',
        stock: 0,
        category: {
            id: 5,
            name: 'Categoría 5',
        },
    },

    {
        id: 6,
        title: 'Producto 6',
        price: 60000,
        description: 'Descripción del producto 6',
        image: 'https://via.placeholder.com/150',
        stock: 8,
        category: {
            id: 6,
            name: 'Categoría 6',
        },  
    },
];

