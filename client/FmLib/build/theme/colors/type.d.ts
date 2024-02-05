import { CSSProperties } from 'react';
import { colorTokens } from '../../tokens/tokens';
type ColorTokens = typeof colorTokens;
type ColorTokenKeys = keyof ColorTokens;
type ColorTokenValues = ColorTokens[ColorTokenKeys];
export type CustomFmThemeType = {
    palette: Partial<Record<ColorTokenKeys, CSSProperties['color']>>;
};
export type InternalFmThemeType = {
    palette: Partial<Record<ColorTokenValues, CSSProperties['color']>>;
};
export {};
