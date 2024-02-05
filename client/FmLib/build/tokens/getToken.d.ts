import tokens from './tokens';
export type Tokens = typeof tokens;
type TokenValues = `var(${Tokens[keyof Tokens]})`;
declare function token<T extends keyof Tokens>(path: T): TokenValues;
export default token;
