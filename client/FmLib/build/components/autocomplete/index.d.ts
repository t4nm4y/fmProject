import { AutocompleteProps } from '@mui/material/Autocomplete';
type FmAutocompleteProps<T> = Omit<AutocompleteProps<T, undefined, undefined, undefined>, 'renderInput' | 'ListboxProps'> & {
    label?: string;
};
declare const Autocomplete: <T>(props: FmAutocompleteProps<T>) => import("@emotion/react/jsx-runtime").JSX.Element;
export { Autocomplete };
