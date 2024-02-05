import React from 'react';
import { CustomFmThemeType } from './colors/type';
interface Props {
    children: React.ReactNode;
    theme: CustomFmThemeType;
    darkTheme?: CustomFmThemeType;
    mode: 'light' | 'dark';
}
declare const FmThemeProvider: ({ children, theme: customTheme, darkTheme, mode }: Props) => import("@emotion/react/jsx-runtime").JSX.Element;
export { FmThemeProvider };
