export class Portfolio {
    name: string;
    total: number;
    available: number;
    invested: number;
    totalHistory: Map<string, number>;
    stocksOwned: Map<string, Array<number>>;
    stocksSold: Map<string, Array<number>>;
}
